

package org.sfu.MotaGame.Bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest{
	//private static final Graphics Graphics2D = null;
//	private Player player;
//	private Board board;
	//Graphics g = (Graphics2D);
//	Graphics g = null;
//	Graphics g;
	//Graphics gMock = Mockito.mock(Graphics.class);

	//Board b;
	private static final Board b = new Board();

//	@Before
	//public void testBoard() {
		//b = new Board();
	//}

	@Test
	public void testGetBoardWidth() {
		b.getWidth();
		assertEquals(32, b.getWidth());
	}

	@Test
	public void testGetBoardHeight() {
		b.getHeight();
		assertEquals(32, b.getHeight());
	}


}