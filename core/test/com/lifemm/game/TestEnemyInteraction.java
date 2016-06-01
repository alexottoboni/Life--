package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Enemy;
import com.lifemm.game.Entity;
import com.lifemm.game.Waldo;
import com.lifemm.game.Collision;
import junit.framework.TestCase;

/**
 * Tests the interaction between Waldo (player), Spider, and Collision classes.
 * @author Hristo Stoytchev
 */

public class TestEnemyInteraction extends TestCase {

      public void testEnemyAttack() {
         Collision detector = new Collision();
         Enemy enemy = new Spider(Entity.Direction.RIGHT);
         Waldo player = new Waldo();
         enemy.setLocationX((float)0);
         player.setX((float)10);
         if (detector.isCollision(enemy.getLocation(), player.getLocation())) {
            player.setHealth(player.getHealth() - enemy.getDamage());
         }
         assertEquals((float)80, player.getHealth(), 0);
      }

      public void testEnemyStop() {
         Collision detector = new Collision();
         Enemy enemy = new Spider(Entity.Direction.RIGHT);
         Waldo player  = new Waldo();
         enemy.setLocationX((float)0);
         player.setX((float)10);
         enemy.updateStateTime();
         if (detector.isCollision(enemy.getLocation(), enemy.getLocation())) {
            if (enemy.getState() != 1) {
               enemy.setState(1);
               player.setHealth(player.getHealth() - enemy.getDamage());
            } else {
               enemy.update();
            }
         }
         assertEquals((float)0, enemy.getX(), 0);
      }
}

