package myPack.graphics;

import myPack.Handler;
import myPack.object.Object;
import myPack.tiles.Tile;

public class GameCamera {
	
	private float xOff, yOff;
	private Handler handler;
	
	public GameCamera(Handler handler, float xOff, float yOff) {
		this.handler = handler;
		this.xOff = xOff;
		this.yOff = yOff;
	}
	
	// Removes whitespace outside the map by bounding the camera
	public void boundCamera() {
		if(xOff < 0) {
			xOff = 0;
		}
		else if(xOff > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
			xOff = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		
		if(yOff < 0) {
			yOff = 0;
		}
		else if( yOff > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
			yOff = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		}
		
		
	}
	
	public void playerCamera(Object o) {
		xOff = o.getX() - handler.getWidth() / 2 + o.getWidth() / 2;
		yOff = o.getY() - handler.getHeight() / 2 + o.getHeight() / 2;
		boundCamera();
	}
	
	public void move(float x, float y) {
		xOff += x;
		yOff += y;
		boundCamera();
	}

	public float getxOff() {
		return xOff;
	}

	public void setxOff(float xOff) {
		this.xOff = xOff;
	}

	public float getyOff() {
		return yOff;
	}

	public void setyOff(float yOff) {
		this.yOff = yOff;
	}
	
}
