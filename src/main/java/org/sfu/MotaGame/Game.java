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
import org.sfu.MotaGame.Bean.player.Player;

public class Game extends Canvas implements Runnable, KeyListener{
	private int width = 1024, height = 1024;
	
	private Board board;
	private ImageData imgData;
	public static Player player;
	
	private boolean isRunning = false;
	
	private Thread thread;

	public Game() {	
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));

		//this.addKeyListener(new KeyBoardListener());
		addKeyListener(this);
		player = new Player(width / 2, height / 2);
	    board = new Board();
 
	}
	
	/*@Override
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
		g2.drawImage(imgData.getHeroImg(board.getDirection()), 32*board.getPx(), 32*board.getPy(), 32, 32, null);
		
		// draw score
		drawScore(g2);
		
		// draw key counter
		drawKeyCounter(g2);
		
		// draw timer
		drawTimer(g2);
	}*/

private void drawGameOver(Graphics2D g2) {
Font font = new Font("Helvetica", Font.BOLD, 20);
//g2.setFont(font);
g2.setColor(Color.BLACK);
g2.fillRect(0, 0, getHeight(), getWidth());
g2.setFont(font);
g2.setColor(Color.RED);
g2.drawString("Game Over! Score: " + board.getScore(), board.getWidth()/2, board.getHeight()/2);	
}

/*
private void drawGameOver(Graphics2D g2) {
	g2.setColor(Color.RED);
    g2.fillRect(0, 0, getHeight(), getWidth());
	g2.drawString("GAME OVER!", 1040 / 2 - ("GAME OVER!").length() - 26, 1063 / 2);
}
*/

/*
	private void drawScore(Graphics2D g2) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g2.setFont(font);
		g2.setColor(Color.yellow);
		g2.drawString("Score: " + board.getScore(), board.getWidth()*board.getWidth()/2 - 500, board.getHeight() - 10);
	}
	
	private void drawKeyCounter(Graphics2D g2) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g2.setFont(font);
		g2.setColor(Color.yellow);
		g2.drawString("Key Fragments: " + board.getKeyCounter(), board.getWidth()*board.getWidth()/2 - 300, board.getHeight() - 10);
	}

	private void drawTimer(Graphics2D g2) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g2.setFont(font);
		g2.setColor(Color.green);
		g2.drawString("Time: " + board.getEstimatedTime()/1000000, board.getWidth()*board.getWidth()/2 + 100, board.getHeight() - 10);
	}
	*/
	
	// Game loop using thread
		public void run() {
			int fps = 60;
			double timePerTick = 1000000000 / fps;
			long lastTime = System.nanoTime();
			double delta = 0;
			long now;
			long timer = 0;
			int ticks = 0;
			
			while(isRunning) {	
				requestFocus();
				now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				
				if(delta >= 1) {
					tick();
					render();
					ticks++;
					delta--;
				}
				
				
				if(timer >= 1000000000) {
					System.out.println("FPS: " + ticks);
					ticks = 0;
					timer = 0;
				}
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
			player.tick();
		}
		
		
		private void render() {
			BufferStrategy b = getBufferStrategy();
			if(b == null) {
				createBufferStrategy(3);
				return;
			}
			
			Graphics g = b.getDrawGraphics();
			g.clearRect(0, 0, height, height);
			
			
			board.render(g);
			player.render(g);
			
			
			g.dispose();
			b.show();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_W)
				player.moveUp = true;
			if(e.getKeyCode() == KeyEvent.VK_S)
				player.moveDown = true;
			if(e.getKeyCode() == KeyEvent.VK_A)
				player.moveLeft = true;
			if(e.getKeyCode() == KeyEvent.VK_D)
				player.moveRight = true;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_W)
				player.moveUp = false;
			if(e.getKeyCode() == KeyEvent.VK_S)
				player.moveDown = false;
			if(e.getKeyCode() == KeyEvent.VK_A)
				player.moveLeft = false;
			if(e.getKeyCode() == KeyEvent.VK_D)
				player.moveRight = false;
		}

}
