package org.sfu.MotaGame.Bean.player;

public class Enemy{

	private int x, y;
	
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
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
