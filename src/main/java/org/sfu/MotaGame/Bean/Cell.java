package org.sfu.MotaGame.Bean;

public class Cell extends Board{
    private int x;
    private int y;

    public Cell(int x, int y){
      this.x = x;
      this.y = y;
    }
    
    public int getX(){
        return x;
      }
      public int getY(){
        return y;
      }
      
  }