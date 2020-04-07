

package org.sfu.MotaGame.Bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest{

	private static final Board b = new Board();
	private static final int width = 32;
	private static final int height = 32;
	private static final int keys = 11;
	private static final int punishments = 14;
	private static final int enemies = 4;
	private static final int walls = 353;
	private static final int playerX = 16;
	private static final int playerY = 30;
	private static final int score = 100;


	@Test
	public void testGetBoardWidth() {
		assertEquals(width, b.getWidth());
	}

	@Test
	public void testGetBoardHeight() {
		assertEquals(height, b.getHeight());
	}

	@Test
	public void testGetBoardKeys() {
		assertEquals(keys, b.getKeyCounter());
	}

	@Test
	public void testGetBoardPunishments() {
		assertEquals(punishments, b.punishments.size());
	}
	
	@Test
	public void testGetBoardEnemies() {
		assertEquals(enemies, b.enemies.size());
	}
	
	@Test
	public void testGetBoardWalls() {
		assertEquals(walls, b.walls.size());
	}
	
	@Test
	public void testGetBoardPlayerX() {
		assertEquals(playerX, b.getPx());
	}
	@Test
	public void testGetBoardPlayerY() {
		assertEquals(playerY, b.getPy());
	}
	
	@Test
	public void testGetBoardScore() {
		assertEquals(score, b.getScore());
	}
}