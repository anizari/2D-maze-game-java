package org.sfu.MotaGame;

//import org.junit.Test;
//import org.junit.jupiter.api.Test;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {    
		
	    JFrame frame = new JFrame("MyGame");
	    frame.setSize(1000,1000);
	    	    
		JPanel game = new Game();
		frame.setContentPane(game);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}