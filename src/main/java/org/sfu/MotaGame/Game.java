package org.sfu.MotaGame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

import org.sfu.MotaGame.Bean.Board;

public class Game {
	
	private JLabel mapLabel;
	private Board board;
	
	public void Run() {
	    JFrame frame = new JFrame("MyGame");
	    frame.setSize(1000,1000);
	    mapLabel = new JLabel("hhhhhh");
	    frame.getContentPane().add(mapLabel); // Adds Button to content pane of frame
	    frame.setVisible(true);
	    
	    board = makeBoard();
	    initBoard();
	    drawBoard();
	    
	    frame.addKeyListener(new KeyBoardListener());
	    
	    Scanner playerInput = new Scanner( System.in );
	    Move(playerInput);
	    
	}
	
	
	private Board makeBoard(){
	    int height = 32;
	    int width = 32;
	    int[][] gameBoard = new int[height][width];
	    int px = 0;
	    int py = 0;
	    Board data = new Board(height, width, gameBoard, px, py);
	    return data;
	}
	
	//idea: initialize everything as 0s, and then add 1s for walls, 2 for enemies etc
	// TODO: should move board init to Board class constructor
	private void initBoard(){
	    int height = board.getHeight();
	    int width = board.getWidth();
	    int[][] gameBoard = board.getBoard();
	    for (int x = 0; x < height; x++ ){
	        for (int y = 0; y < width; y++ ){
	          gameBoard[x][y] = 0;
	        }
	    }
	    int px = board.getPx();
	    int py = board.getPy();
	    gameBoard[px][py] = 1;
	}
	

	private void drawBoard(){
		int height = board.getHeight();
		int width = board.getWidth();
		int[][] gameBoard = board.getBoard();

		StringBuffer sb = new StringBuffer();
		sb.append("<html><p>");
		for(int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				sb.append( gameBoard[i][j] );
				sb.append(" ");
			}
			sb.append("<br />");
		}
		sb.append("</p></html>");

		mapLabel.setText(sb.toString());
	}
	
	private void Move(Scanner playerInput){
		  char playerMove = '\0';
		  boolean isTurnDone = false;
		  int[][] gameBoard = board.getBoard();
		  int px = board.getPx();
		  int py = board.getPy();
		  while(!isTurnDone){
		    playerMove = playerInput.next().charAt(0);
		    switch(playerMove){
		      //Need to check if not out of bounds
		      case 'w':
		      case 'W':
		      if (CheckMove(px - 1, py, gameBoard, board)){
		        gameBoard[px][py] = 0;
		        gameBoard[--px][py] = 1;
		      }
		      break;
		      case 'a':
		      case 'A':
		      if (CheckMove(px, py - 1, gameBoard, board)){
		        gameBoard[px][py] = 0;
		        gameBoard[px][--py] = 1;
		      }
		      break;
		      case 's':
		      case 'S':
		      if (CheckMove(px + 1, py, gameBoard, board)){
		        gameBoard[px][py] = 0;
		        gameBoard[++px][py] = 1;
		      }
		      break;
		      case 'd':
		      case 'D':
		      if (CheckMove(px, py + 1, gameBoard, board)){
		        gameBoard[px][py] = 0;
		        gameBoard[px][++py] = 1;
		      }
		      break;
		    }
		    drawBoard();
		}
	}
	
	//Check for out of bounds + walls
	private static boolean CheckMove(int newX, int newY, int[][] gameBoard, Board board){
	  //out of bounds check
	  if ((newX < 0) | (newY < 0) | (newX >= board.getWidth()) | (newY >= board.getHeight())){
	    return false;
	  }
	  else if (gameBoard[newX][newY] == 9){
	    return false;
	  }
	  else return true;
	}
	
	private class KeyBoardListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
    		System.out.println("we got some shit");
    	}
    	public void keyReleased(KeyEvent e) {
    		
    	}
    	public void keyTyped(KeyEvent e) {
    		
    	}
	}
}
