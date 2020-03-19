

package org.sfu.MotaGame.Bean;

import java.awt.Color;
import java.awt.Font;
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
  
  //ArrayLists for game objects
  public ArrayList<Key> keys;
  public ArrayList<Wall> walls;
  public ArrayList<Punishment> punishments;
  public ArrayList<Enemy> enemies;
  public ArrayList<Bonus> bonus;
  public Exit exit;
  
  
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
			    	  else if (gameBoard[y][x] == 2) {
			    		  exit = new Exit(x*32, y*32);
			    	  }
			    	  else if (gameBoard[y][x] == 4) {
			    		  bonus.add(new Bonus(x*32, y*32));
			    	  }
			      }
			  }
		      //int startX = (int) p.getX();
		      //int startY = (int) p.getY();
		      //gameBoard[startX][startY] = 1;
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
		
		for(int i = 0; i < bonus.size(); i++) {
			bonus.get(i).render(g);
		}
		
		if(keyCounter > 0) {
			exit.renderClosed(g);
		}else {
			exit.renderOpen(g);
		}
		
		drawScore(g);
		drawKeyCounter(g);
		
		
  }
  
  private void drawScore(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.yellow);
		g.drawString("Score: " + score, width * height / 2 - 500, height - 10);
	}
	
  private void drawKeyCounter(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.yellow);
		g.drawString("Key Fragments: " + keyCounter, width * height / 2 - 300, height - 10);
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

}

