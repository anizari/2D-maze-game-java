package org.sfu.MotaGame;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.player.Enemy;
import org.sfu.MotaGame.Bean.player.Player;

public class GameTest {
    private static final int MENU_STATE = 1;
    private static final int GAME_STATE = 2;
    private static final int GAMEOVER_STATE = 3;
    private static final int WIN_STATE = 4;

    private static final Game gm = new Game();
    private static final int height = 32;
    private static final int width = 32;
    private static final int[][] gb = new int[height][width];

    @Test
    public void TestGameOver() {
        assertFalse(gm.isGameOver());
        gm.setState(MENU_STATE);
        assertFalse(gm.isGameOver());
        gm.setState(GAME_STATE);
        assertFalse(gm.isGameOver());
        gm.setState(GAMEOVER_STATE);
        assertTrue(gm.isGameOver());
        gm.setState(WIN_STATE);
        assertTrue(gm.isGameOver());

        gm.setState(GAME_STATE);
        Board b = gm.getBoard();
        b.setScore(-100);
        assertTrue(gm.isGameOver());

        Player p = gm.getPlayer();
        b.setScore(1000);
        gm.setState(GAME_STATE);
        assertFalse(gm.isGameOver());
        Enemy e = b.enemies.get(0);
        e.setX(p.getX());
        e.setY(p.getY());
        gm.collision();
        assertTrue(gm.isGameOver());
    }
}
