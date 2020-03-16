package myPack.graphics;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, currFrame;
	private BufferedImage[] frames;
	private long lastTime;
	private long timer;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		currFrame = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public BufferedImage getCurrFrame() {
		return frames[currFrame];
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			currFrame++;
			timer = 0;
			if(currFrame >= frames.length) {
				currFrame = 0;
			}
		}
	}
}
