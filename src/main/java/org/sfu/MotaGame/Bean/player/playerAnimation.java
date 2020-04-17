package org.sfu.MotaGame.Bean.player;

import java.awt.image.BufferedImage;

/**
 * playerAnimation
 * This class deals with player animation
 *
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class playerAnimation {
    private BufferedImage[] frames;
    private long timer, lastTime;
    private int currentFrame, length;

    /**
     * Constructor for player animation
     *
     * @param frames This parameter deals with the frames
     * @param length This parameter deals with the length
     */
    public playerAnimation(BufferedImage[] frames, int length) {
        this.frames = frames;
        this.length = length;
        timer = 0;
        currentFrame = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * This method animates the player
     *
     */
    public void animate() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > length) {
            currentFrame++;
            timer = 0;
            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }
        }
    }

    /**
     * Getter for player animation
     *
     * @return buffered image
     */
    public BufferedImage getFrame() {
        return frames[currentFrame];
    }
}
