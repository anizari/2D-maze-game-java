package org.sfu.MotaGame.Bean;
import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;
import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.player.Player;

import junit.framework.Assert;

//import static org.mockito.Mockito.*;

public class boardTest{
	//private static final Graphics Graphics2D = null;
//	private Player player;
//	private Board board;
	//Graphics g = (Graphics2D);
//	Graphics g = null;
//	Graphics g;
	//Graphics gMock = Mockito.mock(Graphics.class);
	
	Board b;
	
	@Before
	public void testBoard() {
		b = new Board();
	}
	
	@Test
	public void testGetBoardWidth() {
		assertEquals(32, b.getWidth());
	}
	
	@Test
	public void testGetBoardHeight() {
		assertEquals(32, b.getHeight());
	}
	
}