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

/**
 * <h1>Game</h1>
 * This class deals with creating a new board and player, as well as
 * various game states, running, starting, stopping, updating the game, 
 * checking if the game is over, collisions, checking if keys are pressed,
 * and drawing various screens such as the starting menu or game over screen
 * 
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Game extends Canvas implements Runnable, KeyListener{
	private int width = 1024, height = 1024;
	
	private Board board;
	private ImageData imgData;
	private Player player;
	
	private boolean isRunning = false;
	
	private Thread thread;
	
	private int STATE = 0;
	private int MENU_STATE = 1;
	private int GAME_STATE = 2;
	private int GAMEOVER_STATE = 3;
	private int WIN_STATE = 4;
	
	private int bonusX;
	private int bonusY;
	private boolean bonusFound;
	private int tickCount = 0;

	/**
	 * This method initializes the game
	 * 
	 * @return Nothing
	 */
	public Game() {	
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));

		//this.addKeyListener(new KeyBoardListener());
		addKeyListener(this);
		
		STATE = MENU_STATE;
	    board = new Board();
	    player = new Player(board.getPx()*board.getWidth(), board.getPy()*board.getHeight());
 
	}
	
	/**
	 * This method draws the game's menu
	 * 
	 * @param g This parameter allows us to use the graphics
	 * @return Nothing
	 */
	private void drawMenu(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 40);
		//g2.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Press space to start", board.getWidth()*board.getHeight()/2 - 160, board.getWidth()*board.getHeight()/2);
	}
	
	/**
	 * This method draws the game over screen if the player loses the game
	 * 
	 * @param g This parameter allows us to use the graphics	 
	 * @return Nothing
	 */
	private void drawGameOver(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 40);
		//g2.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Game Over!", board.getWidth()*board.getHeight()/2 - 160, board.getWidth()*board.getHeight()/2);
	}

	/**
	 * This method draws the win screen if the player wins the game
	 * 
	 * @param g This parameter allows us to use the graphics	 
	 * @return Nothing
	 */
	private void drawWin(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 40);
		//g2.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("You win!", board.getWidth()*board.getHeight()/2 - 160, board.getWidth()*board.getHeight()/2);
	}
	
	/**
	 * This method checks if the game should finish, either through a loss or a win
	 * 
	 * @param g This parameter allows us to use the graphics
	 * @return true This returns true if the player has won or lost the game
	 * @return false This returns false if the player has neither won nor lost 
	 */
		private boolean isGameOver() {
			int score = board.getScore();
//			int keyCounter = board.getKeyCounter();
			if(STATE == WIN_STATE) {
				return true;
			}
			if(score < 0) {
				STATE = GAMEOVER_STATE;
				return true;
			}
			else {
				return false;
			}
		}
		
		/**
		 * This method is the main loop for the game
		 * 
		 * @return Nothing.
		 */
		public void run() {
			int fps = 60;
			double timePerTick = 1000000000 / fps;
			long lastTime = System.nanoTime();
			double delta = 0;
			long now;
			long timer = 0;
			int ticks = 0;

			while(isRunning) {	
				isGameOver();
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
		
		/**
		 * This method starts the game
		 * 
		 * @return Nothing.
		 */
		public synchronized void start() {
			if(isRunning) {
				return;
			}
			
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		}
		
		/**
		 * This method stops the game
		 * 
		 * @return Nothing.
		 */
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
		
		/**
		 * This method updates the game each frame
		 * 
		 * @return Nothing.
		 */
		private void tick() {
			if(STATE == GAME_STATE) {
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
				tickCount++;
				if(tickCount % 300 == 0) {
					double tmpX = Math.random()*32;
					double tmpY = Math.random()*32;
					bonusX = (int) tmpX;
					bonusY = (int) tmpY;
					while(board.gameBoard[bonusY][bonusX] != 0) {
						tmpX = Math.random()*32;
						tmpY = Math.random()*32;
						bonusX = (int) tmpX;
						bonusY = (int) tmpY;
					}
					board.gameBoard[bonusY][bonusX] = 4;
					board.bonus.add(new Bonus(bonusX*32, bonusY*32));
					if(board.bonus.size() > 1) {
						board.bonus.remove(0);
					}

				}
			}
		}
		
		/**
		 * This method checks for interactions between game objects
		 * 
		 * @return Nothing.
		 */
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
			for(int i = 0; i < board.enemies.size(); i++) {
				Enemy e = board.enemies.get(i);

				// enemy player collision
				if (player.getBoundsTop().intersects(e.getBounds())) {
					STATE = GAMEOVER_STATE;
				}
				if (player.getBoundsBottom().intersects(e.getBounds())) {
					STATE = GAMEOVER_STATE;
				}
				if (player.getBoundsLeft().intersects(e.getBounds())) {
					STATE = GAMEOVER_STATE;
				}
				if (player.getBoundsRight().intersects(e.getBounds())) {
					STATE = GAMEOVER_STATE;
				}

				// enemy wall collision
				for(int j = 0; j < board.walls.size(); j++) {
					Wall w = board.walls.get(j);

					if (e.getBoundsTop().intersects(w.getBounds())) {
						e.setY(w.getY() + w.getHeight());
						e.setVelY(0);
					}
					if (e.getBoundsBottom().intersects(w.getBounds())) {
						e.setY(w.getY() - w.getHeight());
						e.setVelY(0);
					}
					if (e.getBoundsLeft().intersects(w.getBounds())) {
						e.setX(w.getX() + w.getWidth());
						e.setVelX(0);
					}
					if (e.getBoundsRight().intersects(w.getBounds())) {
						e.setX(w.getX() - w.getWidth());
						e.setVelX(0);
					}
				}
			}
			if (player.getBoundsTop().intersects(board.exit.getBounds())) {
				if(board.getKeyCounter() == 0) {
					STATE = WIN_STATE;
				}
				else {
					player.setY(board.exit.getY() + board.exit.getHeight());
					player.setVelY(0);
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
			for(int i = 0; i < board.bonus.size(); i++) {
				if (player.getBoundsTop().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}
				if (player.getBoundsBottom().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}
				if (player.getBoundsLeft().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}
				if (player.getBoundsRight().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}	
			}
			for(int i = 0; i < board.bonus.size(); i++) {
				if (player.getBoundsTop().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}
				if (player.getBoundsBottom().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}
				if (player.getBoundsLeft().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
					break;
				}
				if (player.getBoundsRight().intersects(board.bonus.get(i).getBounds())) {
					board.bonus.remove(i);
					board.setScore(board.getScore() + 200);
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
		
		/**
		 * This method renders the graphical component of the game
		 * 
		 * 
		 * @return Nothing.
		 */
		private void render() {
			BufferStrategy b = getBufferStrategy();
			if(b == null) {
				createBufferStrategy(3);
				return;
			}
			
			Graphics g = b.getDrawGraphics();
			g.clearRect(0, 0, height, height);
			
			if(STATE == MENU_STATE) {
				drawMenu(g);
			}
			else if(STATE == GAME_STATE) {
				board.render(g);
				player.render(g);
			}
			else if(STATE == GAMEOVER_STATE) {
				drawGameOver(g);
			}
			else if(STATE == WIN_STATE) {
				drawWin(g);
			}
			g.dispose();
			b.show();
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		
		/**
		 * This method checks when a key is pressed
		 * 
		 * @param e This parameter checks for key events
		 * @return Nothing.
		 */
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			if(STATE == MENU_STATE) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					STATE = GAME_STATE;
					board.startTimer();
				}

			}
			else if(STATE == GAME_STATE) {
				if(e.getKeyCode() == KeyEvent.VK_W)
					player.moveUp = true;
				if(e.getKeyCode() == KeyEvent.VK_S)
					player.moveDown = true;
				if(e.getKeyCode() == KeyEvent.VK_A)
					player.moveLeft = true;
				if(e.getKeyCode() == KeyEvent.VK_D)
					player.moveRight = true;
			}
			
		}

		
		/**
		 * This method checks when a key is released
		 * 
		 * @param e This parameter checks for key events
		 * @return Nothing.
		 */
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

