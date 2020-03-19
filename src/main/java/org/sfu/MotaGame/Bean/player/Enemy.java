package org.sfu.MotaGame.Bean.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

public class Enemy extends Rectangle{

	private int x, y;
	private ImageData imgData = new ImageData();
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
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
	
}
