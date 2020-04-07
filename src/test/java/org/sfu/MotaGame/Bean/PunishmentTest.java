package org.sfu.MotaGame.Bean;

import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.Punishment;
import org.sfu.MotaGame.Bean.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class PunishmentTest {
	
	@Test
	public void testCheckPunishments() {
		Board b = new Board();
		Player player = new Player(0, 0);
		
		b.punishments = new ArrayList<Punishment>();
		b.punishments.add(new Punishment(100, 100));
		
		b.setScore(100);
		
		player.setX(100);
		player.setY(100);
		b.checkPunishments(player);
		Assertions.assertEquals(0, b.getScore());
		
		
	}
	
}
