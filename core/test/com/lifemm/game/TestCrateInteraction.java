package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Spider;
import com.lifemm.game.Bat;
import com.lifemm.game.Bear;
import com.lifemm.game.Crate;
import com.lifemm.game.Collision;
import junit.framework.TestCase;

/**
 * Tests the interaction between the Crate, Spider, Bat, Bear,
 * and Collision classes
 * @author Sonia Tomas
 */

public class TestCrateInteraction extends TestCase {

   public void testCrateAndSpiderCollision() {
      Collision collisionDetector = new Collision();
      Spider spider = new Spider();
      Crate crate = new Crate(600);
      spider.setLocationX(600);
      if (collisionDetector.isCollision(spider.getLocation(), crate.getLocation()))
      {
         crate.setHealth(crate.getHealth() - spider.getDamage());
      }
      assertEquals(30, crate.getHealth(), 0.1);
   }
 
   public void testCrateAndBatCollision() {
      Collision collisionDetector = new Collision();
      Bat bat  = new Bat();
      Crate crate = new Crate(600);
      bat.setLocationX(600);
      if (collisionDetector.isCollision(bat.getLocation(), crate.getLocation()))
      {
         crate.setHealth(crate.getHealth() - bat.getDamage());
      }
      assertEquals(40, crate.getHealth(), 0.1);
   }

   public void testCrateAndBearCollision() {
      Collision collisionDetector = new Collision();
      Bear bear = new Bear();
      Crate crate = new Crate(600);
      bear.setLocationX(600);
      if (collisionDetector.isCollision(bear.getLocation(), crate.getLocation()))
      {
         crate.setHealth(crate.getHealth() - bear.getDamage());
      }
      assertEquals(0, crate.getHealth(), 0.1);
   }
}
