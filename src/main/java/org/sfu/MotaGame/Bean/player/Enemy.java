package org.sfu.MotaGame.Bean.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

public class Enemy extends Rectangle{

	private int x, y;
	float distX;
	float distY;
	private ImageData imgData = new ImageData();
	private int velX = 1;
	private int velY = 1;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		x += velX;
		y += velY;
	}
	
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
	
	public void setVelX(int x) {
		this.velX = x;
	}
	
	public void setVelY(int y) {
		this.velY = y;
	}
	
}
