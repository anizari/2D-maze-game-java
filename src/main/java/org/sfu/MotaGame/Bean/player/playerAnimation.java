package org.sfu.MotaGame.Bean.player;

import java.awt.image.BufferedImage;

public class playerAnimation {
	private BufferedImage[] frames;
	private long timer, lastTime;
	private int currentFrame, length;
	
	public playerAnimation(BufferedImage[] frames, int length) {
		this.frames = frames;
		this.length = length;
		timer = 0;
		currentFrame = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void animate() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > length) {
			currentFrame++;
			timer =  0;
			if(currentFrame >= frames.length) {
				currentFrame = 0;
			}
		}
	}
	
	public BufferedImage getFrame() {
		return frames[currentFrame];
	}
}
