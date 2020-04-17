package org.sfu.MotaGame.Bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Wall
 * This class implements the walls for the game
 *
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Wall extends Rectangle {
    private ImageData imgData = new ImageData();

    /**
     * This method sets the boundaries for the walls
     *
     * @param x This parameter represents the first coordinate of the Wall object
     * @param y This parameter represents the second coordinate of the Wall object
     */
    public Wall(int x, int y) {
        setBounds(x, y, 32, 32);
    }

    /**
     * This method draws the walls
     *
     * @param g This parameter allows us to use graphics.
     */
    public void render(Graphics g) {
        g.drawImage(imgData.get(9), x, y, 32, 32, null);
    }
}
