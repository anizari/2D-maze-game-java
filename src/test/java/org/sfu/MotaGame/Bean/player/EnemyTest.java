package org.sfu.MotaGame.Bean.player;

import java.awt.Rectangle;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class EnemyTest {

    private static final int width = 32;
    private static final int height = 32;

    @Test
    public void TestCheckCollision() {
        Enemy enemy = new Enemy(100, 100);

        // no collision
        Rectangle r = new Rectangle(50, 50, width, height);
        assert (!enemy.checkCollision(r));

        // collision with top
        r = new Rectangle(100, 110, width, height);
        assert (enemy.checkCollision(r));

        // collision with bottom
        r = new Rectangle(100, 90, width, height);
        assert (enemy.checkCollision(r));

        // collision with left
        r = new Rectangle(90, 100, width, height);
        assert (enemy.checkCollision(r));

        // collision with right
        r = new Rectangle(110, 100, width, height);
        assert (enemy.checkCollision(r));

        // collision with bottom-left
        r = new Rectangle(90, 90, width, height);
        assert (enemy.checkCollision(r));
    }

    @Test
    public void TestX() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(100, enemy.getX());
        enemy.setX(200);
        Assertions.assertEquals(200, enemy.getX());
    }

    @Test
    public void TestY() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(100, enemy.getY());
        enemy.setY(200);
        Assertions.assertEquals(200, enemy.getY());
    }

    @Test
    public void TestVelX() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(0, enemy.getVelX());
        enemy.setVelX(3);
        Assertions.assertEquals(3, enemy.getVelX());
    }

    @Test
    public void TestVelY() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(0, enemy.getVelY());
        enemy.setVelY(3);
        Assertions.assertEquals(3, enemy.getVelY());
    }

    @Test
    public void TestDistX() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(0, enemy.getDistX());
        enemy.setDistX(100);
        Assertions.assertEquals(100, enemy.getDistX());
    }

    @Test
    public void TestDistY() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(0, enemy.getDistY());
        enemy.setDistY(100);
        Assertions.assertEquals(100, enemy.getDistY());
    }

    @Test
    public void TestGetDistance() {
        Enemy enemy = new Enemy(100, 100);
        Assertions.assertEquals(0, enemy.getDistance());
        enemy.setDistance(150);
        Assertions.assertEquals(150, enemy.getDistance());
    }


}
