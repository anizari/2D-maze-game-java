
package org.sfu.MotaGame.Bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import java.awt.Rectangle;
import org.sfu.MotaGame.Bean.player.Enemy;
import org.sfu.MotaGame.Bean.player.Player;


enum dir {UP, DOWN, LEFT, RIGHT}


/**
 * <h1>Board</h1>
 * This class deals with initializing and rendering all necessary game objects,
 * using an integer array as input for reference.
 * 
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Board{
  private int height;
  private int width;
  private ImageData imgData;

  private int px;
  private int py;

  //ArrayLists for game objects
  public ArrayList<Key> keys;
  public ArrayList<Wall> walls;
  public ArrayList<Punishment> punishments;
  public ArrayList<Enemy> enemies;
  public ArrayList<Bonus> bonus;
  public Exit exit;
  
  public int[][] gameBoard;
  
  
  //Testing score
  private int score = 100;
  //counts the number of key fragments left
  private int keyCounter = 0;

  long startTime;
  long time;
  
  /**
   * This method initializes a board object by calling
   * the initBoard function
   * 
   * @return Nothing.
   */
  public Board(){
	  initBoard();
	}
  
  /**
   * This method initializes our game board
   * 
   * @return Nothing.
   */
  public void initBoard() {
	// read map from file
		  try {
			  walls = new ArrayList<Wall>();
			  keys = new ArrayList<Key>();
			  enemies = new ArrayList<Enemy>();
			  punishments = new ArrayList<Punishment>();
			  bonus = new ArrayList<Bonus>();
			  Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/src/main/resources/world1.txt"));
			  this.height = scanner.nextInt();
			  this.width = scanner.nextInt();
			  
			  this.py = scanner.nextInt();
			  this.px = scanner.nextInt();

			  this.gameBoard = new int[height][width];
			  for (int y = 0; y < height; y++ ){
			      for (int x = 0; x < width; x++ ){
			    	  gameBoard[y][x] = scanner.nextInt();
			    	  
			    	  if (gameBoard[y][x] == 9) {
			    		  walls.add(new Wall(x*32, y*32));
			    	  }
			    	  //counts keys in world1.txt file
			    	  else if (gameBoard[y][x] == 7) {
			    		  this.keyCounter++;
			    		  keys.add(new Key(x*32, y*32));
			    	  }
			    	  //counts enemies
			    	  else if (gameBoard[y][x] == 3) {
			    		  enemies.add(new Enemy(x*32, y*32));
			    	  }
			    	  //counts punishments
			    	  else if (gameBoard[y][x] == 8) {
			    		  punishments.add(new Punishment(x*32, y*32));
			    	  }
			    	  else if (gameBoard[y][x] == 2) {
			    		  exit = new Exit(x*32, y*32);
			    	  }
			    	  else if (gameBoard[y][x] == 4) {
			    		  bonus.add(new Bonus(x*32, y*32));
			    	  }
			      }
			  }
		      scanner.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
  }

	/**
	 *
	 * @param player
	 * @return true if player winds the game
	 */
  public boolean checkExit(Player player) {
	  if (player.checkCollision(this.exit)) {
		  if(this.getKeyCounter() == 0) {
		  	  return true;
		  }
		  else {
			  player.setY(this.exit.getY() + this.exit.getHeight());
			  player.setVelY(0);
		  }
	  }

	  return false;
  }
  
  public void checkPunishments(Player player) {
	  for(int i = 0; i < this.punishments.size(); i++) {
		  if (player.checkCollision(this.punishments.get(i))) {
			  this.punishments.remove(i);
			  this.setScore(this.getScore() - 100);
			  break;
		  }
	  }
  }
  
  public void checkBonus(Player player) {
	  for(int i = 0; i < this.bonus.size(); i++) {
			if (player.checkCollision(this.bonus.get(i))) {
				this.bonus.remove(i);
				this.setScore(this.getScore() + 200);
				break;
			}
		}
  }

  public void checkKeys(Player player) {
	  for(int i = 0; i < this.keys.size(); i++) {
		  if (player.checkCollision(this.keys.get(i))) {
			  this.keys.remove(i);
			  this.setScore(this.getScore() + 100);
			  this.setKeyCounter(this.getKeyCounter() - 1);
			  break;
		  }
	  }
  }

	/**
   * This method starts the timer for our game
   * 
   * @return Nothing.
   */

  public void startTimer() {
	  startTime = System.currentTimeMillis();
  }

  /**
   * This method renders our game's graphics
   * 
   * @param g This parameter lets us use graphics
   * @return Nothing.
   */
  public void render(Graphics g) {
	  imgData = new ImageData();

		for(int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				g.drawImage(imgData.get(0), 32*j, 32*i, 32, 32, null);
			}
		}
		
		for(int i = 0; i < walls.size(); i++) {
			walls.get(i).render(g);
		}
		
		for(int i = 0; i < keys.size(); i++) {
			keys.get(i).render(g);
		}
		
		for(int i = 0; i < punishments.size(); i++) {
			punishments.get(i).render(g);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		
		for(int i = 0; i < bonus.size(); i++) {
			bonus.get(i).render(g);
		}
		
		if(keyCounter > 0) {
			exit.renderClosed(g);
		}else {
			exit.renderOpen(g);
		}
		
		drawScore(g);
		drawKeyCounter(g);
		drawTimer(g);
		
		
  }
  /**
   * This method draws the score for our game
   * 
   * @param g This parameter lets us use graphics
   * @return Nothing.
   */
  private void drawScore(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.yellow);
		g.drawString("Score: " + score, width * height / 2 - 500, height - 10);
	}
  
  /**
   * This method draws the key counter showing how many keys are remaining in our game
   * 
   * @param g This parameter lets us use graphics
   * @return Nothing.
   */
  private void drawKeyCounter(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.yellow);
		g.drawString("Key Fragments: " + keyCounter, width * height / 2 - 300, height - 10);
  }
  
  /**
   * This method draws the timer
   * 
   * @param g2 This parameter lets us use graphics
   * @return Nothing.
   */
  private void drawTimer(Graphics g2) {
  	  Font font = new Font("Helvetica", Font.BOLD, 20);
  	  g2.setFont(font);
  	  g2.setColor(Color.green);
  	  time = (System.currentTimeMillis() - this.startTime) / 1000;
  	  g2.drawString("Time: " + time, this.getWidth()*this.getWidth()/2 + 100, this.getHeight() - 10);
  }
  
  public long getTime() {
	return time;
  }

  /**
   * This method gets the height of our board
   * 
   * @return height
   */
  public int getHeight(){
    return height;
  }
  
  /**
   * This method gets the width of our board
   * 
   * @return width
   */
  public int getWidth(){
    return width;
  }
  
  /**
   * This method gets the 2D integer array
   * 
   * @return gameBoard
   */
  public int[][] getBoard(){
    return gameBoard;
  }
  
  /**
   * This method gets the x coordinate of our player
   * 
   * @return px
   */
  public int getPx(){
    return px;
  }
  
  /**
   * This method gets the y coordinate of our player
   * 
   * @return py
   */
  public int getPy(){
    return py;
  }
  
  /**
   * This method gets the score
   * 
   * @return score
   */
  public int getScore() {
	  return score;
  }
  
  /**
   * This method gets the amount of keys in our board
   * 
   * @return keyCounter
   */
  public int getKeyCounter() {
	  return keyCounter;
  }
	
  /**
   * This method sets the amount of keys in our board
   * 
   * @return Nothing
   */
  public void setKeyCounter(int keyCounter) {
	  this.keyCounter = keyCounter;
  }
	
  /**
   * This method sets the score in our board
   * 
   * @return Nothing
   */
  public void setScore(int score) {
	  this.score = score;
  }

}

