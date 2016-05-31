package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Spider;
import com.lifemm.game.Treasure;
import com.lifemm.game.Collision;
import junit.framework.TestCase;

/**
 * Tests the interaction between the Treasure, Spider, and Collision classes.
 * @author Alex Ottoboni (aottobon)
 */

public class TestTreasureInteraction extends TestCase {

   public void testTreasureDamage() {
      Collision colDetector = new Collision();
      Spider newSpider = new Spider();
      Treasure treasure = new Treasure();
      newSpider.setLocationX(590);
      if (colDetector.isCollision(newSpider.getLocation(), treasure.getLocation())) {
         treasure.setHealth(treasure.getHealth() - newSpider.getDamage());
      }
      assertEquals(1480, treasure.getHealth(), 0.1);
   }

   public void testSpiderTreasureImpact() {
      Collision detector = new Collision();
      Spider spider1 = new Spider();
      Treasure treasure = new Treasure();
      spider1.setLocationX(588);
      spider1.updateStateTime();
      if (detector.isCollision(spider1.getLocation(), treasure.getLocation())) {
         if (spider1.getState() != 1) {
            spider1.setState(1);
            treasure.setHealth(treasure.getHealth() - spider1.getDamage());
         } else {
            spider1.update();
         }
      }
      assertEquals(588, spider1.getX(), 1);
   }
}
