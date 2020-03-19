package org.sfu.MotaGame.Bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Rectangle{
	private ImageData imgData = new ImageData();

	
	public Wall(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.drawImage(imgData.get(9), x, y, 32, 32, null);
	}
}
