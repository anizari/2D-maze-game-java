package org.sfu.MotaGame.Bean.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.sfu.MotaGame.Bean.ImageData;

public class Player extends Rectangle{

	//private int x, y;
	private ImageData imgData = new ImageData();
	public boolean moveUp, moveDown, moveLeft, moveRight;
	private int velX = 2; 
	private int velY = 2;
	
	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void tick() {
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
	public void setX(double x) {
		this.x = (int) x;
	}
	public void setY(double y) {
		this.y = (int) y;
	}
	
	public void setVelX(int x) {
		this.velX = x;
	}
	
	public void setVelY(int y) {
		this.velY = y;
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
