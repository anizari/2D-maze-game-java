package org.sfu.MotaGame.Bean;

import org.sfu.MotaGame.Bean.Board;
import org.sfu.MotaGame.Bean.Key;
import org.sfu.MotaGame.Bean.player.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class KeyTest {

    @Test
    public void testCheckKeys() {
        Board b = new Board();

        b.keys = new ArrayList<Key>();
        b.keys.add(new Key(100, 100));
        b.keys.add(new Key(200, 200));
        b.setScore(0);
        b.setKeyCounter(2);


        //get key
        Player player = new Player(100, 100);
        b.checkKeys(player);
        Assertions.assertEquals(100, b.getScore());
        Assertions.assertEquals(1, b.getKeyCounter());
        Assertions.assertEquals(1, b.keys.size());

        player.setX(210);
        player.setY(200);
        b.checkKeys(player);
        Assertions.assertEquals(200, b.getScore());
        Assertions.assertEquals(0, b.getKeyCounter());
        Assertions.assertEquals(0, b.keys.size());

        //do not get key
        b.checkKeys(player);
        Assertions.assertEquals(200, b.getScore());
        Assertions.assertEquals(0, b.getKeyCounter());

    }
}
