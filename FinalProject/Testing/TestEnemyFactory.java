package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.EnemyFactory;

import junit.framework.TestCase;

/**
 * Tests the EnemyFactory object. Tests integration between EnemyFactory and Enemy classes.
 * @author Jerrick Fong
 */

public class TestEnemyFactory extends TestCase {

    public void testGetEnemyBear() {
      EnemyFactory ef = new EnemyFactory();
      Enemy bear = ef.getEnemy("Bear", Entity.Direction.RIGHT);
      
      assertEquals(50, bear.getDamage());
    }

    public void testGetEnemyBatLeft() {
      EnemyFactory ef = new EnemyFactory();
      Enemy bat = ef.getEnemy("bat", Entity.Direction.LEFT);
      
      assertEquals(Entity.Direction.LEFT, bat.getDirection());
    }
}
