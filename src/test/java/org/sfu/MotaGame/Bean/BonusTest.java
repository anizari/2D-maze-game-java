package org.sfu.MotaGame.Bean;

import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.Bonus;
import org.sfu.MotaGame.Bean.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class BonusTest {

	@Test
	public void testCheckBonus() {
		Board b = new Board();
		Player player = new Player(0, 0);
		
		b.bonus = new ArrayList<Bonus>();
		b.bonus.add(new Bonus(100, 100));
		
		b.setScore(0);
		
		//Moves into bonus
		player.setX(100);
		player.setY(100);
		b.checkBonus(player);
		Assertions.assertEquals(200, b.getScore());
		
		//No move
		b.checkBonus(player);
		Assertions.assertEquals(200, b.getScore());
		
	}
	
}
