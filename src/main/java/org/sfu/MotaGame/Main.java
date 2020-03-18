package org.sfu.MotaGame;

//import org.junit.Test;
//import org.junit.jupiter.api.Test;
import javax.swing.*;


public class Main {
		
	public static void main(String[] args) {    
		Game game = new Game();
	    JFrame frame = new JFrame("MyGame");
	    frame.add(game);
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		game.start();
		
	}
}