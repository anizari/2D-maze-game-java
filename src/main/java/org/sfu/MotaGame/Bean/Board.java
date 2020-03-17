package org.sfu.MotaGame.Bean;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

enum dir {UP, DOWN, LEFT, RIGHT}

public class Board{
  private int height;
  private int width;
  private int[][] gameBoard;
  //Take out and leave in Cell class?
  private Cell[][] cells;

  private int px;
  private int py;
  
  private dir playerFacing;
  

  public Board(){
	  
	  // read map from file
	  try {
		  Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/sprite/world1.txt"));
		  this.height = scanner.nextInt();
		  this.width = scanner.nextInt();
		  
		  this.px = scanner.nextInt();
		  this.py = scanner.nextInt();
		  this.playerFacing = dir.DOWN;
		  
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
  
  public dir getDirection() {
	  return this.playerFacing;
  }

  //Check for out of bounds + walls
  private boolean CheckMove(int newX, int newY){
    //out of bounds check
    if ((newX < 0) || (newY < 0) || (newX >= width) || (newY >= height)) {
      return false;
    }
    else if (gameBoard[newX][newY] == 9){
      return false;
    }
    else return true;
  }

  public void movePlayer(int direction){
    switch (direction){
      case KeyEvent.VK_UP:
        if (CheckMove(px - 1, py)){
          gameBoard[px][py] = 0;
          gameBoard[--px][py] = 1;
          this.playerFacing = dir.UP;
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


  

  public Board(int height, int width, int[][] gameBoard, int px, int py){
    this.height = height;
    this.width = width;
    this.gameBoard = gameBoard;
    this.px = px;
    this.py = py;
  }
  
  
  //Take out and leave in Cell class?
  public Cell[][] getCells(){
    return cells;
  }

  public class Cell{
    private int x;
    private int y;
    private Cell[][] cells;

    public int getX(){
      return x;
    }
    public int getY(){
      return y;
    }
    public Cell[][] getCells(){
      return cells;
    }
    public Cell(int x, int y, Cell[][] cells){
      this.x = x;
      this.y = y;
      this.cells = cells;
    }
  }

}
