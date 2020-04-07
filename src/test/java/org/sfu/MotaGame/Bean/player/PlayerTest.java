

package org.sfu.MotaGame.Bean.player;


import java.awt.Rectangle;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class PlayerTest{

    private static final int width = 32;
    private static final int height = 32;
    Player player = new Player(100, 100);

    @Test
    public void testCheckCollision() {
        // no collision
        Rectangle r = new Rectangle(50, 50, width, height);
        assert(!player.checkCollision(r));

        // collision with top
        r = new Rectangle(100, 110, width, height);
        assert (player.checkCollision(r));

        // collision with bottom
        r = new Rectangle(100, 90, width, height);
        assert (player.checkCollision(r));

        // collision with left
        r = new Rectangle(90, 100, width, height);
        assert (player.checkCollision(r));

        // collision with right
        r = new Rectangle(110, 100, width, height);
        assert (player.checkCollision(r));

        // collision with bottom-left
        r = new Rectangle(90, 90, width, height);
        assert (player.checkCollision(r));
    }
    
    @Test
    public void testCoordinates() {
    	assertEquals(100, player.getX(), 0.0);
    	assertEquals(100, player.getY(), 0.0);
    	player.setX(200);
    	player.setY(300);
    	assertEquals(200, player.getX(), 0.0);
    	assertEquals(300, player.getY(), 0.0);
    }
    
    @Test
    public void testVelocity() {
    	assertEquals(2, player.getVelX());
    	assertEquals(2, player.getVelY());
    	player.setVelX(3);
    	player.setVelY(4);
    	assertEquals(3, player.getVelX());
    	assertEquals(4, player.getVelY());
    }
}