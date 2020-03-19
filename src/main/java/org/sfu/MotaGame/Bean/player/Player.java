package org.sfu.MotaGame.Bean.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

public class Player extends Rectangle{

	//private int x, y;
	private ImageData imgData = new ImageData();
	public boolean moveUp, moveDown, moveLeft, moveRight;
	private int speed = 2;
	
	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void tick() {
		if(moveUp)
			y -= speed;
		if(moveDown)
			y += speed;
		if(moveLeft)
			x -= speed;
		if(moveRight)
			x += speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
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
