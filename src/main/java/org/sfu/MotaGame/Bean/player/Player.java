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
	
	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
		
		up = new playerAnimation(ImageData.heroUp, 124);
		down = new playerAnimation(ImageData.heroDown, 124);
		left = new playerAnimation(ImageData.heroLeft, 124);
		right = new playerAnimation(ImageData.heroRight, 124);

	}
	
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
	
	public void render(Graphics g) {
		
			if(velY < 0) {
				g.drawImage(up.getFrame(), x, y, width, height, null);
			}
			else if(velY > 0) {
				g.drawImage(down.getFrame(), x, y, width, height, null);
			}
			else if(velX < 0) {
				g.drawImage(left.getFrame(), x, y, width, height, null);
			}
			else {
				g.drawImage(right.getFrame(), x, y, width, height, null);
			}
		
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
