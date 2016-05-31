package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Enemy;
import com.lifemm.game.Entity;
import com.badlogic.gdx.math.Rectangle;
import junit.framework.TestCase;

/**
 * Tests the Enemy objects.
 * @author Hristo Stoytchev
 */

public class TestEnemy extends TestCase {
    public void testGetDamageSpider() {
        Enemy enemy = new Spider(Entity.Direction.RIGHT);
        assertEquals(20, enemy.getDamage());
    }

    public void testGetDamageBear() {
	Enemy enemy = new Bear(Entity.Direction.RIGHT);
	assertEquals(50, enemy.getDamage());
    }

    public void testSetLocationXSinglePoint() {
	Enemy enemy = new Spider(Entity.Direction.RIGHT);
	enemy.setLocationX((float)4.2);
	
	assertEquals((float)4.2, enemy.getLocation().getX(), 0);
    }

    public void testGetAttackIntervalSpider() {
	Enemy enemy = new Spider(Entity.Direction.RIGHT);
	assertEquals(50, enemy.getAttackInterval());
    }

    public void testGetAttackIntervalBear() {
	Enemy enemy = new Bear(Entity.Direction.RIGHT);
	assertEquals(100, enemy.getAttackInterval());
    }

}
