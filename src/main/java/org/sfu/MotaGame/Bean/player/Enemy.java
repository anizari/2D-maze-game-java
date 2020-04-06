package org.sfu.MotaGame.Bean.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

/**
 * <h1>Enemy</h1>
 * This class renders the enemies and contains all of the relevant
 * variables and constructors including the distance between the enemies and the player
 *  
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Enemy extends Rectangle{
	private double x, y;
	private double distX;
	private double distY;
	private double distance;
	private ImageData imgData = new ImageData();
	private double velX;
	private double velY;

	/**
	 * Constructor for the Enemy class
	 * 
	 */
	public Enemy(int x, int y) {
		setBounds(x, y, 32, 32);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Deals with updating the enemy's position every frame.
	 * 
	 */
	public void tick() {
		x += velX;
		y += velY;
	}
	
	public boolean checkCollision(Rectangle boardEntity) {
		return this.getBoundsTop().intersects(boardEntity.getBounds()) ||
				this.getBoundsBottom().intersects(boardEntity.getBounds()) ||
				this.getBoundsLeft().intersects(boardEntity.getBounds()) ||
				this.getBoundsRight().intersects(boardEntity.getBounds());
	}
	
	/**
	 * This method draws the enemies
	 * 
	 * @param g This parameter allows us to use graphics.
	 * @return Nothing.
	 */
	public void render(Graphics g) {
		g.drawImage(imgData.get(3), (int)(Math.round(x)), (int)(Math.round(y)), 32, 32, null);
	}
	
	/**
	 * Getter for the x coordinate 
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Getter for the y coordinate 
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Setter for the x coordinate 
	 * 
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Setter for the y coordinate 
	 * 
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Setter for the x coordinate of the velocity 
	 * 
	 */
	public void setVelX(double x) {
		this.velX = x;
	}
	
	/**
	 * Setter for the y coordinate of the velocity 
	 * 
	 */
	public void setVelY(double y) {
		this.velY = y;
	}
	
	/**
	 * Getter for the x coordinate of the distance
	 * 
	 * @return distX
	 */
	public double getDistX() {
		return distX;
	}
	
	/**
	 * Getter for the y coordinate of the distance
	 * 
	 * @return distY
	 */
	public double getDistY() {
		return distY;
	}
	
	/**
	 * Setter for the x coordinate of the distance
	 * 
	 */
	public void setDistX(double x) {
		this.distX = x;
	}
	
	/**
	 * Setter for the y coordinate of the distance
	 * 
	 */
	public void setDistY(double y) {
		this.distY = y;
	}
	
	/**
	 * Getter for the distance
	 * 
	 * @return distance
	 */
	public double getDistance() {
		return distance;
	}
	
	/**
	 * Setter for the distance
	 * 
	 */
	public void setDistance(double d) {
		this.distance = d;
	}
	
	/**
	 * Getter for the bounds of the rectangle Object
	 * 
	 * @return Rectangle object
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	/**
	 * Getter for the top bounds of the rectangle Object
	 * 	 
	 * * @return Rectangle object
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y, (int) width / 2, (int) height / 2);
	}

	/**
	 * Getter for the bottom bounds of the rectangle Object
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y + (height / 2), (int) width / 2, (int) height / 2);
	}

	/**
	 * Getter for the right bounds of the rectangle Object
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width - 5, (int) y + 4, (int) 5, (int) height - 8);
	}

	/**
	 * Getter for the left bounds of the rectangle Object
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 4, 5, height - 8);
	}
}