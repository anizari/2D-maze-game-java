package org.sfu.MotaGame.Bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Punishment extends Rectangle {
	public Punishment(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
