package org.sfu.MotaGame.Bean.player;

import java.awt.Color;
import java.awt.Graphics;

import org.sfu.MotaGame.Bean.ImageData;

public class Player{

	private int x, y;
	private ImageData imgData;
	private boolean up, down, left, right;
	private static final int speed = 2;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(up)
			y -= speed;
		if(down)
			y += speed;
		if(left)
			x -= speed;
		if(right)
			x += speed;
	}
	
	public void render(Graphics g) {
		//Temp player
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}
