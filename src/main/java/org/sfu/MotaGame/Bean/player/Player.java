package org.sfu.MotaGame.Bean.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;


public class Player extends Rectangle{

	public boolean moveUp, moveDown, moveLeft, moveRight;
	private int velX = 2; 
	private int velY = 2;

	//Animate the player
	private playerAnimation up, down, left, right;
	
	/**
	 * This method initialized the player coordinates
	 * and size of the player object. The values for the player
	 * animations are also initialized.
	 * 
	 *
	 * @param x This is the x-coord of the player object
	 * @param y This is the y-coord of the player object
	 * @return Nothing.
	 */
	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
		
		up = new playerAnimation(ImageData.heroUp, 124);
		down = new playerAnimation(ImageData.heroDown, 124);
		left = new playerAnimation(ImageData.heroLeft, 124);
		right = new playerAnimation(ImageData.heroRight, 124);

	}
	
	/**
	 * This method updates the player at every tick
	 * 
	 * @return Nothing.
	 */
	public void tick() {
		up.animate();
		down.animate();
		left.animate();
		right.animate();
		
		velX = 2;
		velY = 2;
		if(moveUp)
			y -= velY;
		if(moveDown)
			y += velY;
		if(moveLeft)
			x -= velX;
		if(moveRight)
			x += velX;
	}
	
	/**
	 * This method renders the player graphics
	 * including animations
	 * 
	 * @param g 
	 * @return Nothing.
	 */
	public void render(Graphics g) {
		
		if(moveUp) {
			g.drawImage(up.getFrame(), x, y, width, height, null);
		}
		else if(moveDown) {
			g.drawImage(down.getFrame(), x, y, width, height, null);
		}
		else if(moveLeft) {
			g.drawImage(left.getFrame(), x, y, width, height, null);
		}
		else if(moveRight) {
			g.drawImage(right.getFrame(), x, y, width, height, null);
		}else {
			g.drawImage(ImageData.hero, x, y, width, height, null);
		}
	
}	
	
	/**
	 * This method gets the x value
	 * 
	 * @return x.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * This method gets theyx value
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * This method sets the x value
	 * 
	 * @return Nothing
	 */
	public void setX(double x) {
		this.x = (int) x;
	}
	
	/**
	 * This method sets the y value
	 * 
	 * @return Nothing
	 */
	public void setY(double y) {
		this.y = (int) y;
	}
	
	/**
	 * This method sets the velX value
	 * 
	 * @return Nothing
	 */
	public void setVelX(int x) {
		this.velX = x;
	}
	
	/**
	 * This method sets the velY value
	 * 
	 * @return Nothing
	 */
	public void setVelY(int y) {
		this.velY = y;
	}
	
	@Override
	/**
	 * This method gets the bounds of a rectangle
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	/**
	 * This method gets the top bounds of a rectangle
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y, (int) width / 2, (int) height / 2);
	}
	
	/**
	 * This method gets the bottom bounds of a rectangle
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y + (height / 2), (int) width / 2, (int) height / 2);
	}
	
	/**
	 * This method gets the right bounds of a rectangle
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width - 5, (int) y + 4, (int) 5, (int) height - 8);
	}
	
	/**
	 * This method gets the left bounds of a rectangle
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 4, 5, height - 8);
	}
	
	
	
}
