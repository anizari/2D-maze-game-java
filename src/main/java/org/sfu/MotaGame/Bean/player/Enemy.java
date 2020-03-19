package org.sfu.MotaGame.Bean.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

public class Enemy extends Rectangle{

	private int x, y;
	private double distX;
	private double distY;
	private double distance;
	private ImageData imgData = new ImageData();
	private double velX;
	private double velY;
	
	public Enemy(int x, int y) {
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
		g.drawImage(imgData.get(3), x, y, 32, 32, null);
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
	
}

