package com.lifemm.game;

import org.junit.Ignore;
import org.junit.Test;
import com.lifemm.game.Waldo;

import static org.junit.Assert.*;

/**
 * Tests the Waldo object.
 * @author Alex Ottoboni
 */

public class TestWaldo {
    @Test
    public void testUpdateStateTimeIntialValue() {
        Waldo waldo = new Waldo();
        assertEquals(0, waldo.getTimeInState());
    }

    @Test
    public void testSetStateChangedState() {
        Waldo waldo = new Waldo();
        waldo.setState(1);
        waldo.updateStateTime();
        waldo.updateState();
        assertEquals(1, waldo.getState());
    }

   @Test
   public void testSetStateExpiredState() {
      Waldo waldo = new Waldo();
      waldo.setState(1);
      for (int i = 0; i < 45; i++) {
         waldo.updateStateTime();
      }
      waldo.updateState();
      assertEquals(2, waldo.getState());
   }

   @Test
   public void testGetLivesInitial() {
      Waldo waldo = new Waldo();
      assertEquals(3, waldo.getLives());
   }

   @Test
   public void testDecrementLivesLostOne() {
      Waldo waldo = new Waldo();
      waldo.decrementLives();
      assertEquals(2, waldo.getLives());
   }
}
