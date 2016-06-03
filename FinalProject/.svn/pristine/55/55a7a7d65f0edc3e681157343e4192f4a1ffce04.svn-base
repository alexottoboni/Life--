package com.lifemm.game;

import org.junit.Test;
import junit.framework.TestCase;
import com.lifemm.game.Treasure;

/**
 * Tests the Treasure object.
 * @author Sonia Tomas
 */

public class TestTreasure extends TestCase {
   public void testGetHeightInitial() {
      Treasure treasure = new Treasure();
      assertEquals(256.0, treasure.getHeight(), 0);
   }

   public void testGetHealthInitial() {
      Treasure treasure = new Treasure();
      assertEquals(1500.0, treasure.getHealth(), 0);
   }

   public void testAddHealthIncreaseTwenty() {
      Treasure treasure = new Treasure();
      treasure.addHealth((float)20.0);
      assertEquals(1520.0, treasure.getHealth(), 0);
   }

   public void testAddHealthDecreaseFifty() {
      Treasure treasure = new Treasure();
      treasure.addHealth((float)-50.0);
      assertEquals(1450.0, treasure.getHealth(), 0);
   }
   
   public void testGetWidthInitial(){
      Treasure treasure = new Treasure();
      assertEquals(256.0, treasure.getWidth(), 0); 
   }
}
