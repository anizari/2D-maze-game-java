package org.sfu.MotaGame;

import javax.swing.*;

/*
 * <h1>Main</h1>
 * Main creates a new Game, adds a new frame and then starts the game
 * 
 * 
 * 
 * @author Christopher Kassner, Alim Nizari, Thomas Chiu, John Ye
 * @version 1.0
 * @since 2020-03-18
 */
public class Main {
		
	public static void main(String[] args) {    
		Game game = new Game();
	    JFrame frame = new JFrame("Game");
	    frame.add(game);
	    //frame.setSize(1040,1063);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		
		game.start();
		
	}
}
