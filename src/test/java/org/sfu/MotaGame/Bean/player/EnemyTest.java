package org.sfu.MotaGame.Bean.player;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;

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

}
