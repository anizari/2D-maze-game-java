package org.sfu.MotaGame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.sfu.MotaGame.Bean.Board;

public class Game extends JPanel{
	
	private JLabel mapLabel;
	private Board board;

	public Game() {		
	    mapLabel = new JLabel();
	    this.add(mapLabel); // Adds Button to content pane of frame
	    
	    board = makeBoard();
	    initBoard();

	    
	    this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.addKeyListener(new KeyBoardListener());
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
				if(x == 0 || x==(height-1)){
					gameBoard[x][y] = 9;
				}
				else {
					gameBoard[x][y] = 0;
				}
	        }
	    }
	    int px = board.getPx();
	    int py = board.getPy();
	    gameBoard[px][py] = 1;
	}
	
	@Override
	public void paintComponent(Graphics g){
		BufferedImage gameBgImg;
		try {
			gameBgImg = ImageIO.read(new File(System.getProperty("user.dir") + "/sprite/store.png"));
		} catch(Exception e) {
			gameBgImg = null;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(gameBgImg, null, 0, 0);
		
		
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

	
	private class KeyBoardListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
    		int keyCode = e.getKeyCode();
    		board.movePlayer(keyCode);

    		repaint();
    	}
    	public void keyReleased(KeyEvent e) {
    		
    	}
    	public void keyTyped(KeyEvent e) {
    		
    	}
	}

}
