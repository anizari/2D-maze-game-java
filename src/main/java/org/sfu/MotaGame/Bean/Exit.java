package org.sfu.MotaGame.Bean;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>Exit</h1>
 * This class implements the exit for the game
 * 
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Exit extends Rectangle{
	
	private ImageData imgData = new ImageData();

	/**
	 * This method sets the boundaries for the exit
	 * 
	 * @param x This parameter represents the first coordinate of the Exit object
	 * @param y This parameter represents the second coordinate of the Exit object
	 * @return Nothing.
	 */
	public Exit(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	/**
	 * This method draws the closed door
	 * 
	 * @param g This parameter allows us to use graphics.
	 * @return Nothing.
	 */
	public void renderClosed(Graphics g) {
		g.drawImage(imgData.get(2), x, y, width, height, null);
	}
	
	/**
	 * This method draws the open door
	 * 
	 * @param g This parameter allows us to use graphics.
	 * @return Nothing.
	 */
	public void renderOpen(Graphics g) {
		g.drawImage(imgData.get(5), x, y, width, height, null);
	}
	
}
