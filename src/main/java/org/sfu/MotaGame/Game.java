package org.sfu.MotaGame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.sfu.MotaGame.Bean.*;

public class Game extends JPanel implements Runnable{
	
	private Board board;
	private ImageData imgData;
	
	private boolean isRunning = false;
	
	private Thread thread;

	public Game() {		
		imgData = new ImageData();
	    board = new Board();

	    this.setFocusable(true);
	    this.requestFocusInWindow();
	    this.addKeyListener(new KeyBoardListener());
	}
	
	//Game Loop
	public void run() {
		while(isRunning) {
			tick();
			render();
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(isRunning) {
			return;
		}
		
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!isRunning) {
			return;
		}
		
		isRunning = false;
		try {
			thread.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void tick() {
		
	}
	
	
	private void render() {
		
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
		
		// draw score
		drawScore(g2);
		
		//drawTimer(g2);
	}
	
	private void drawScore(Graphics2D g2) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g2.setFont(font);
		g2.setColor(Color.yellow);
		g2.drawString("Score: " + board.getScore(), board.getWidth()*board.getWidth()/2 - 400, board.getHeight() - 10);
	}

	/*private void drawTimer(Graphics2D g2) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g2.setFont(font);
		g2.setColor(Color.green);
		g2.drawString("Time: " + board.getEstimatedTime(), board.getWidth()*board.getWidth()/2 - 500, board.getHeight() - 10);
	}*/
	
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
