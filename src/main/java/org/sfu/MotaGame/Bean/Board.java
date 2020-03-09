package org.sfu.MotaGame.Bean;

import java.awt.event.KeyEvent;

public class Board{
  private int height;
  private int width;
  private int[][] gameBoard;
  //Take out and leave in Cell class?
  private Cell[][] cells;

  private int px;
  private int py;

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
        }
        break;
      case KeyEvent.VK_LEFT:
        if (CheckMove(px, py - 1)){
          gameBoard[px][py] = 0;
          gameBoard[px][--py] = 1;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (CheckMove(px + 1, py)){
          gameBoard[px][py] = 0;
          gameBoard[++px][py] = 1;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (CheckMove(px, py + 1)){
          gameBoard[px][py] = 0;
          gameBoard[px][++py] = 1;
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
