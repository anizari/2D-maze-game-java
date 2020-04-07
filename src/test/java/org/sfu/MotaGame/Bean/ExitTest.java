package org.sfu.MotaGame.Bean;

import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.Exit;
import org.sfu.MotaGame.Bean.player.Player;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;


public class ExitTest {
	
	@Test
	public void testCheckExit() {
		Board b = new Board();
		Player player = new Player(0, 0);
		
		b.exit = new Exit(100, 100);
		
		
		
		//Moves into exit with keys remaining
		b.setKeyCounter(1);
		player.setX(100);
		player.setY(100);
		Assertions.assertFalse(b.checkExit(player));
		
		player.setX(0);
		player.setY(0);
		
		//Moves into exit with no keys remaining
		b.setKeyCounter(0);
		player.setX(100);
		player.setY(100);
		Assertions.assertTrue(b.checkExit(player));
		
	}
	
}
