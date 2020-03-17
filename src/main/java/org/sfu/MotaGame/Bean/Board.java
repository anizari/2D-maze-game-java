
package org.sfu.MotaGame.Bean;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;


enum dir {UP, DOWN, LEFT, RIGHT}

public class Board{
  private int height;
  private int width;
  private int[][] gameBoard;

  private int px;
  private int py;
  
  //Testing score
  private int score = 100;
  
  private dir playerFacing;
  
  long startTime = System.nanoTime()/1000;
//  long startTime = System.currentTimeMillis()/1000;
//  long endTime = System.currentTimeMillis()/1000 - startTime;
  
  public Board(){
	  initBoard();
	}
  
  public void initBoard() {
	// read map from file
		  try {
			  Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/sprite/world1.txt"));
			  this.height = scanner.nextInt();
			  this.width = scanner.nextInt();
			  
			  this.px = scanner.nextInt();
			  this.py = scanner.nextInt();
			  this.playerFacing = dir.DOWN;
			  //this.score = score;
			  
			  this.gameBoard = new int[height][width];
			  for (int x = 0; x < height; x++ ){
			      for (int y = 0; y < width; y++ ){
			    	  gameBoard[x][y] = scanner.nextInt();
			      }
			  }
		      gameBoard[px][py] = 1;
		      scanner.close();
		  } catch (Exception e) {
			  e.printStackTrace();
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
  
  public dir getDirection() {
	  return this.playerFacing;
  }

  //Check for out of bounds + walls
  private boolean CheckMove(int newX, int newY){
	  while(score > 0) {
    //out of bounds check
    if ((newX < 0) || (newY < 0) || (newX >= width) || (newY >= height)) {
      return false;
    }
    else if (gameBoard[newX][newY] == 9){
      return false;
    }
    else if (gameBoard[newX][newY] == 7) {
    	score = score + 100;
    	System.out.println(score);
    	long estimatedTime = System.nanoTime()/1000 - startTime;
    	System.out.println(estimatedTime/1000000 + " seconds");
   // 	System.out.println("Took : " + startTime);
    	return true;
    }
    else if (gameBoard[newX][newY] == 8) {
    	score = score - 100;
    	System.out.println(score);    	
    	long estimatedTime = System.nanoTime()/1000 - startTime;
    	System.out.println(estimatedTime/1000000 + " seconds");
    	return true;
    }
    else if (gameBoard[newX][newY] == 4) {
    	score = score + 200;
    	System.out.print(score);
 //      	System.out.println("Took : " + startTime);
    	return true;
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
        if (CheckMove(px - 1, py)){
          gameBoard[px][py] = 0;
          gameBoard[--px][py] = 1;
          this.playerFacing = dir.UP;
//          System.out.println(endTime);
        }
        break;
      case KeyEvent.VK_LEFT:
        if (CheckMove(px, py - 1)){
          gameBoard[px][py] = 0;
          gameBoard[px][--py] = 1;
          this.playerFacing = dir.LEFT;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (CheckMove(px + 1, py)){
          gameBoard[px][py] = 0;
          gameBoard[++px][py] = 1;
          this.playerFacing = dir.DOWN;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (CheckMove(px, py + 1)){
          gameBoard[px][py] = 0;
          gameBoard[px][++py] = 1;
          this.playerFacing = dir.RIGHT;
        };
    }

  }

 
  
}
