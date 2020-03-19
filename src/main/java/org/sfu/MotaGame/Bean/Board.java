

package org.sfu.MotaGame.Bean;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.sfu.MotaGame.Bean.player.Enemy;
import org.sfu.MotaGame.Bean.player.Player;


enum dir {UP, DOWN, LEFT, RIGHT}

public class Board{
  private int height;
  private int width;
  private int[][] gameBoard;
  private ImageData imgData;

  private int px;
  private int py;
  
  private Player p;
  private Enemy[] e;
  
  //ArrayLists for game objects
  private ArrayList<Key> keys;
  private ArrayList<Wall> walls;
  private ArrayList<Punishment> punishments;
  private ArrayList<Enemy> enemies;
  
  
  //Testing score
  private int score = 100;
  //counts the number of key fragments left
  private int keyCounter = 0;
  private dir playerFacing;
  
  long startTime = System.nanoTime()/1000;
  long estimatedTime;
//  long startTime = System.currentTimeMillis()/1000;
//  long endTime = System.currentTimeMillis()/1000 - startTime;
  
  public Board(){
	  initBoard();
	}
  
  //initializes our gameboard
  public void initBoard() {
	// read map from file
		  try {
			  walls = new ArrayList<>();
			  keys = new ArrayList<>();
			  enemies = new ArrayList<>();
			  punishments = new ArrayList<>();
			  Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/sprite/world1.txt"));
			  this.height = scanner.nextInt();
			  this.width = scanner.nextInt();
			  
			  this.py = scanner.nextInt();
			  this.px = scanner.nextInt();
			  this.p = new Player(px, py);
			  this.playerFacing = dir.DOWN;
			  
			  this.e = new Enemy[scanner.nextInt()];
			  int eCounter = 0;
			  //this.score = score;
			  
			  this.gameBoard = new int[height][width];
			  for (int y = 0; y < height; y++ ){
			      for (int x = 0; x < width; x++ ){
			    	  gameBoard[y][x] = scanner.nextInt();
			    	  
			    	  if (gameBoard[y][x] == 9) {
			    		  walls.add(new Wall(x*32, y*32));
			    	  }
			    	  //counts keys in world1.txt file
			    	  else if (gameBoard[y][x] == 7) {
			    		  this.keyCounter++;
			    		  keys.add(new Key(x*32, y*32));
			    	  }
			    	  //counts enemies
			    	  else if (gameBoard[y][x] == 3) {
			    		  enemies.add(new Enemy(x*32, y*32));
			    	  }
			    	  //counts punishments
			    	  else if (gameBoard[y][x] == 8) {
			    		  punishments.add(new Punishment(x*32, y*32));
			    	  }
			      }
			  }
		      int startX = (int) p.getX();
		      int startY = (int) p.getY();
		      gameBoard[startX][startY] = 1;
		      scanner.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
  }
  
  public void render(Graphics g) {
	  imgData = new ImageData();

		for(int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				g.drawImage(imgData.get(0), 32*j, 32*i, 32, 32, null);
			}
		}
		
		for(int i = 0; i < walls.size(); i++) {
			walls.get(i).render(g);
		}
		
		for(int i = 0; i < keys.size(); i++) {
			keys.get(i).render(g);
		}
		
		for(int i = 0; i < punishments.size(); i++) {
			punishments.get(i).render(g);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		
		
  }
  
  public int getHeight(){
    return height;
  }
  public int getWidth(){
    return width;
  }
  public int[][] getBoard(){
    return gameBoard;
  }
  public int getPx(){
    return px;
  }
  public int getPy(){
    return py;
  }
  
  //Testing score
  public int getScore() {
	  return score;
  }
  
  

  
  public long getEstimatedTime() {
	return estimatedTime;
}

public void setEstimatedTime(long estimatedTime) {
	this.estimatedTime = estimatedTime;
}


public int getKeyCounter() {
	return keyCounter;
}

public void setKeyCounter(int keyCounter) {
	this.keyCounter = keyCounter;
}

public void setScore(int score) {
	this.score = score;
}

public dir getDirection() {
	  return this.playerFacing;
}

//Check for out of bounds + walls
private boolean CheckMove(int newY, int newX){
	  while(score > 0) {
  //out of bounds check
  if ((newX < 0) || (newY < 0) || (newX >= width) || (newY >= height)) {
    return false;
  }
  //wall check
  else if (gameBoard[newY][newX] == 9){
    return false;
  }
  //key fragment check
  else if (gameBoard[newY][newX] == 7) {
  	score = score + 100;
  	System.out.println(score);
  	keyCounter--;
  	System.out.println(keyCounter);
  	estimatedTime = System.nanoTime()/1000 - startTime;
//   	System.out.println(estimatedTime/1000000 + " seconds");
 // 	System.out.println("Took : " + startTime);
  	return true;
  }
  else if (gameBoard[newY][newX] == 8) {
  	score = score - 100;
  	System.out.println(score);    	
  	estimatedTime = System.nanoTime()/1000 - startTime;
//  	System.out.println(estimatedTime/1000000 + " seconds");
  	return true;
  }
  else if (gameBoard[newY][newX] == 4) {
  	score = score + 200;
  	System.out.print(score);
//      	System.out.println("Took : " + startTime);
  	return true;
  }
  else if (gameBoard[newY][newX] == 2) {
	  if (keyCounter == 0) {
		  return true;
	  }
	  else {
		  return false;
	  }
  }
  else{
  	return true;
  }
	  }
	  return false;
}

public void movePlayer(int direction){
    switch (direction){
      case KeyEvent.VK_UP:
        if (CheckMove(py - 1, px)){
          gameBoard[py][px] = 0;
          gameBoard[--py][px] = 1;
          this.playerFacing = dir.UP;
//          System.out.println(endTime);
        }
        break;
      case KeyEvent.VK_LEFT:
        if (CheckMove(py, px - 1)){
          gameBoard[py][px] = 0;
          gameBoard[py][--px] = 1;
          this.playerFacing = dir.LEFT;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (CheckMove(py + 1, px)){
          gameBoard[py][px] = 0;
          gameBoard[++py][px] = 1;
          this.playerFacing = dir.DOWN;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (CheckMove(py, px + 1)){
          gameBoard[py][px] = 0;
          gameBoard[py][++px] = 1;
          this.playerFacing = dir.RIGHT;
        };
    }

  }

 
  
}

