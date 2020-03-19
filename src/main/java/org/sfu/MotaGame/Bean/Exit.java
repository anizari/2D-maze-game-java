package org.sfu.MotaGame.Bean;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Exit extends Rectangle{
	
	private ImageData imgData = new ImageData();
	
	public Exit(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void renderClosed(Graphics g) {
		g.drawImage(imgData.get(2), x, y, width, height, null);
	}
	
	public void renderOpen(Graphics g) {
		g.drawImage(imgData.get(5), x, y, width, height, null);
	}
}
