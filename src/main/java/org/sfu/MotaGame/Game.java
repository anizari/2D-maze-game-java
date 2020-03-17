package org.sfu.MotaGame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.sfu.MotaGame.Bean.*;

public class Game extends JPanel{
	
	private Board board;
	private ImageData imgData;

	public Game() {		
		imgData = new ImageData();
	    board = new Board();

	    this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.addKeyListener(new KeyBoardListener());
	}
	
	
	
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		int height = board.getHeight();
		int width = board.getWidth();
		int[][] gameBoard = board.getBoard();

		for(int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				g2.drawImage(imgData.get(gameBoard[i][j]), 32*j, 32*i, 32, 32, null);
			}
		}
		
		// draw hero
		g2.drawImage(imgData.getHeroImg(board.getDirection()), 32*board.getPy(), 32*board.getPx(), 32, 32, null);
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
