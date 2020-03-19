package org.sfu.MotaGame.Bean.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

public class Enemy extends Rectangle{

	private double x, y;
	private double distX;
	private double distY;
	private double distance;
	private ImageData imgData = new ImageData();
	private double velX;
	private double velY;
	
	public Enemy(int x, int y) {
		setBounds(x, y, 32, 32);
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		x += velX;
		y += velY;
	}
	
	/*
	 * This method draws the enemies
	 * 
	 * @param g This parameter allows us to use graphics.
	 * @return Nothing.
	 */
	public void render(Graphics g) {
		g.drawImage(imgData.get(3), (int)(Math.round(x)), (int)(Math.round(y)), 32, 32, null);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVelX(double x) {
		this.velX = x;
	}
	
	public void setVelY(double y) {
		this.velY = y;
	}
	
	public double getDistX() {
		return distX;
	}
	
	public double getDistY() {
		return distY;
	}
	
	public void setDistX(double x) {
		this.distX = x;
	}
	
	public void setDistY(double y) {
		this.distY = y;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double d) {
		this.distance = d;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y, (int) width / 2, (int) height / 2);
	}
	
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width / 2) - ((width / 2) / 2), (int) y + (height / 2), (int) width / 2, (int) height / 2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width - 5, (int) y + 4, (int) 5, (int) height - 8);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 4, 5, height - 8);
	}

	
}

