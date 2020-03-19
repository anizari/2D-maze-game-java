package org.sfu.MotaGame.Bean;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bonus extends Rectangle{
	private ImageData imgData = new ImageData();
	
	public Bonus(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.drawImage(imgData.get(4), x, y, 32, 32, null);
	}
}
