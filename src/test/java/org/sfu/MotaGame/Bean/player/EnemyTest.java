package org.sfu.MotaGame.Bean.player;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnemyTest {
	
	private static final int width = 32;
    private static final int height = 32;

	@Test
	public void testCheckCollision() {
        Enemy enemy = new Enemy(100, 100);

        // no collision
        Rectangle r = new Rectangle(50, 50, width, height);
        assert(!enemy.checkCollision(r));

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
	public void testX() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(100, enemy.getX());
		enemy.setX(200);
		Assertions.assertEquals(200, enemy.getX());
	}

	@Test
	public void testY() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(100, enemy.getY());
		enemy.setY(200);
		Assertions.assertEquals(200, enemy.getY());
	}
	
	@Test
	public void testVelX() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(0, enemy.getVelX());
		enemy.setVelX(3);
		Assertions.assertEquals(3, enemy.getVelX());
	}
	
	@Test
	public void testVelY() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(0, enemy.getVelY());
		enemy.setVelY(3);
		Assertions.assertEquals(3, enemy.getVelY());
	}
	
	@Test
	public void testDistX() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(0, enemy.getDistX());
		enemy.setDistX(100);
		Assertions.assertEquals(100, enemy.getDistX());
	}
	
	@Test
	public void testDistY() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(0, enemy.getDistY());
		enemy.setDistY(100);
		Assertions.assertEquals(100, enemy.getDistY());
	}
	
	@Test
	public void testGetDistance() {
		Enemy enemy = new Enemy(100, 100);
		Assertions.assertEquals(0, enemy.getDistance());
		enemy.setDistance(150);
		Assertions.assertEquals(150, enemy.getDistance());
	}
	
	
}
