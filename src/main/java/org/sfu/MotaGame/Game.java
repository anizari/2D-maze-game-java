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
import org.sfu.MotaGame.Bean.player.Enemy;
import org.sfu.MotaGame.Bean.player.Player;

public class Game extends Canvas implements Runnable, KeyListener{
	private int width = 1024, height = 1024;
	
	private Board board;
	private ImageData imgData;
	private Player player;
	
	private boolean isRunning = false;
	
	private Thread thread;

	public Game() {	
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));

		//this.addKeyListener(new KeyBoardListener());
		addKeyListener(this);
	    board = new Board();
	    player = new Player(board.getPx()*board.getWidth(), board.getPy()*board.getHeight());
 
	}

	private void drawGameOver(Graphics2D g2) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		//g2.setFont(font);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getHeight(), getWidth());
		g2.setFont(font);
		g2.setColor(Color.RED);
		g2.drawString("Game Over! Score: " + board.getScore(), board.getWidth()/2, board.getHeight()/2);	
	}
	
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
			for(int i = 0; i < board.enemies.size(); i++) {
				Enemy tmp = board.enemies.get(i);
				tmp.setDistX(tmp.getX() - player.getX() - tmp.getWidth());
				tmp.setDistY(tmp.getY() - player.getY() - tmp.getHeight());
				tmp.setDistance(Math.sqrt((tmp.getX() - player.getX()) * (tmp.getX() - player.getX()) + (tmp.getY() - player.getY()) * (tmp.getY() - player.getY())));
				tmp.setVelX((-1 / tmp.getDistance()) * tmp.getDistX());
				tmp.setVelY((-1 / tmp.getDistance()) * tmp.getDistY());
				tmp.tick();
			}
			player.tick();
			
			collision();
		}
		
		private void collision() {
			for(int i = 0; i < board.walls.size(); i++) {
				Wall tmp = board.walls.get(i);
				if (player.getBoundsTop().intersects(tmp.getBounds())) {
					player.setY(tmp.getY() + tmp.getHeight());
					player.setVelY(0);
				}
				if (player.getBoundsBottom().intersects(tmp.getBounds())) {
					player.setY(tmp.getY() - tmp.getHeight());
					player.setVelY(0);
				}
				if (player.getBoundsLeft().intersects(tmp.getBounds())) {
					player.setX(tmp.getX() + tmp.getWidth());
					player.setVelX(0);
				}
				if (player.getBoundsRight().intersects(tmp.getBounds())) {
					player.setX(tmp.getX() - tmp.getWidth());
					player.setVelX(0);
				}
			}
			
			for(int i = 0; i < board.keys.size(); i++) {
				if (player.getBoundsTop().intersects(board.keys.get(i).getBounds())) {
					board.keys.remove(i);
					board.setScore(board.getScore() + 100);
					board.setKeyCounter(board.getKeyCounter() - 1);
					break;
				}
				if (player.getBoundsBottom().intersects(board.keys.get(i).getBounds())) {
					board.keys.remove(i);
					board.setScore(board.getScore() + 100);
					board.setKeyCounter(board.getKeyCounter() - 1);
					break;
				}
				if (player.getBoundsLeft().intersects(board.keys.get(i).getBounds())) {
					board.keys.remove(i);
					board.setScore(board.getScore() + 100);
					board.setKeyCounter(board.getKeyCounter() - 1);
					break;
				}
				if (player.getBoundsRight().intersects(board.keys.get(i).getBounds())) {
					board.keys.remove(i);
					board.setScore(board.getScore() + 100);
					board.setKeyCounter(board.getKeyCounter() - 1);
					break;
				}	
			}
			
			for(int i = 0; i < board.punishments.size(); i++) {
				if (player.getBoundsTop().intersects(board.punishments.get(i).getBounds())) {
					board.punishments.remove(i);
					board.setScore(board.getScore() - 100);
					break;
				}
				if (player.getBoundsBottom().intersects(board.punishments.get(i).getBounds())) {
					board.punishments.remove(i);
					board.setScore(board.getScore() - 100);
					break;
				}
				if (player.getBoundsLeft().intersects(board.punishments.get(i).getBounds())) {
					board.punishments.remove(i);
					board.setScore(board.getScore() - 100);
					break;
				}
				if (player.getBoundsRight().intersects(board.punishments.get(i).getBounds())) {
					board.punishments.remove(i);
					board.setScore(board.getScore() - 100);
					break;
				}
				
				
			}
			
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
