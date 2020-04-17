package org.sfu.MotaGame.Bean;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

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

    //Test board width getter
    @Test
    public void TestGetBoardWidth() {
        assertEquals(width, b.getWidth());
    }

    //Test board height getter
    @Test
    public void TestGetBoardHeight() {
        assertEquals(height, b.getHeight());
    }

    //Test board key counter getter
    @Test
    public void TestGetBoardKeys() {
        assertEquals(keys, b.getKeyCounter());
    }

    //Test board punishment size
    @Test
    public void TestGetBoardPunishments() {
        assertEquals(punishments, b.punishments.size());
    }

    //Test board enemies size
    @Test
    public void TestGetBoardEnemies() {
        assertEquals(enemies, b.enemies.size());
    }

    //Test board walls size
    @Test
    public void TestGetBoardWalls() {
        assertEquals(walls, b.walls.size());
    }

    //Test board player x position getter
    @Test
    public void TestGetBoardPlayerX() {
        assertEquals(playerX, b.getPx());
    }

    //Test board player y position getter
    @Test
    public void TestGetBoardPlayerY() {
        assertEquals(playerY, b.getPy());
    }

    //Test board score getter
    @Test
    public void TestGetBoardScore() {
        assertEquals(score, b.getScore());
    }
}