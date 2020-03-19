package org.sfu.MotaGame.Bean;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <h1>Bonus</h1>
 * This class implements the bonus rewards for the game
 * 
 * 
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, Yuzhuo Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Bonus extends Rectangle{
	private ImageData imgData = new ImageData();
	
	/**
	 * This method sets the boundaries for the bonus
	 * 
	 * @param x This parameter represents the first coordinate of the Bonus object
	 * @param y This parameter represents the second coordinate of the Bonus object
	 * @return Nothing.
	 */
	public Bonus(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	/**
	 * This method draws the bonus item
	 * 
	 * @param g This parameter allows us to use graphics.
	 * @return Nothing.
	 */
	public void render(Graphics g) {
		g.drawImage(imgData.get(4), x, y, 32, 32, null);
	}
}