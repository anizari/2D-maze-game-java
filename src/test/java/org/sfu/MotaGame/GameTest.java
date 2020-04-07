package org.sfu.MotaGame;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import org.hamcrest.Matcher;
//import org.junit.Before;
//import org.junit.Test;
import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.player.Player;

//import junit.framework.Assert;

public class GameTest{
	private static final Graphics Graphics2D = null;
//	private Player player;
//	private Board board;
	Graphics g = (Graphics2D);

	private static final int STATE = 0;
	private static final int MENU_STATE = 1;
	private static final int GAME_STATE = 2;
	private static final int GAMEOVER_STATE = 3;
	private static final int WIN_STATE = 4;

	//private static final Board b = new Board();
	private static final Player p = new Player(100, 100);
	private static final Game gm = new Game();
	private static final int height = 32;
	private static final int width = 32;
	private static final int[][] gb = new int[height][width];
	
	private boolean isRunning = false;
//	private static final boolean isRunning = false;
	public boolean moveUp, moveDown, moveLeft, moveRight;
	
	
	/*@Before
	public void setup() {
		Board b = new Board();
		Game gm = new Game();
	}*/
	//	player = new Player(board.getPx()*board.getWidth(), board.getPy()*board.getHeight());
	
	@Test
	public void shouldGameBeOver() {
		assertFalse(gm.isGameOver());
		gm.setState(MENU_STATE);
		assertFalse(gm.isGameOver());
		gm.setState(GAME_STATE);
		assertFalse(gm.isGameOver());
		gm.setState(GAMEOVER_STATE);
		assertTrue(gm.isGameOver());
		gm.setState(WIN_STATE);
		assertTrue(gm.isGameOver());
	}

	
	/*@Test
	public void shouldStart() {
		if(!isRunning) {
			isRunning = true;
			assertTrue(isRunning);
		}
	}*/



//	KeyEvent e = addKeyListener(this);
	/*
	@Test
	public void isKeyPressed() {
		KeyEvent e = null;
//		assertTrue(e.getKeyCode(), moveUp);
	//	if(e.getKeyCode() == KeyEvent.VK_W) {
//			assertThat(player.moveUp, is(true));
//		KeyEvent e = keyPressed(KeyEvent e);
//		KeyEvent e = new KeyEvent(null, KeyEvent.KEY_PRESSED, 0, 0, 0);
//		Game();
//		KeyEvent e = addKeyListener(null);
	//	Game gm = new Game();		
		//Player p = new Player(100, 100);
	//	keyPressed(KeyEvent e);
	//	if(e.getKeyCode() == KeyEvent.VK_W) {
		//	assertTrue(player.moveUp);
			//assertEquals(true, player.moveUp);
		}*/
	
	
	//@Test
//	public void shouldRun() {
		//if(isRunning){
			//assertEquals()
//		}
	//}
	
	/*
	@Test
	public void shouldRender() {
		Graphics g = b.getDrawGraphics();
		if(STATE == MENU_STATE) {
			assertTrue(g.drawMenu());
		}
	}*
	

	/*
	@Test
	public void shouldMoveWhenKeyPressed() {    
		player = new Player(board.getPx()*board.getWidth(), board.getPy()*board.getHeight());
		Assert.assertEquals(, actual);*/
	
}
