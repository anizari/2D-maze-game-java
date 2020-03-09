package org.sfu.MotaGame.Bean;

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
