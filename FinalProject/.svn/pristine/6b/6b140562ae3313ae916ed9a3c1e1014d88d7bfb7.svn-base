package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.Waldo;
import junit.framework.TestCase;

/**
 * Tests the Waldo object.
 * @author Alex Ottoboni
 */

public class TestWaldo extends TestCase {
    public void testUpdateStateTimeIntialValue() {
        Waldo waldo = new Waldo();
        assertEquals(0, waldo.getTimeInState());
    }

    public void testSetStateChangedState() {
        Waldo waldo = new Waldo();
        waldo.setState(1);
        waldo.updateStateTime();
        waldo.updateState();
        assertEquals(1, waldo.getState());
    }

   public void testSetStateExpiredState() {
      Waldo waldo = new Waldo();
      waldo.setState(1);
      for (int i = 0; i < 45; i++) {
         waldo.updateStateTime();
      }
      waldo.updateState();
      assertEquals(2, waldo.getState());
   }

   public void testGetLivesInitial() {
      Waldo waldo = new Waldo();
      assertEquals(3, waldo.getLives());
   }

   public void testDecrementLivesLostOne() {
      Waldo waldo = new Waldo();
      waldo.decrementLives();
      assertEquals(2, waldo.getLives());
   }

   // Loop is never entered
   public void TestUpdateStateLoopInitial() {
      Waldo waldo = new Waldo();
      assertEquals(2, waldo.getState()); 
   }

   // Loop is entered once
   public void TestUpdateStateLoopOnce() {
      Waldo waldo = new Waldo();
      waldo.setState(4);
      for (int i = 0; i < 1; i++) {
         waldo.updateState();
      }
      assertEquals(2, waldo.getState());
   }

   // Test loop at the boundary of state change
   public void TestUpdateStateLoopBoundary() {
      Waldo waldo = new Waldo();
      waldo.setState(4);
      for (int i = 0; i < 101; i++) {
         waldo.updateState();
      }
      assertEquals(2, waldo.getState());
   }

   // Test loop past the boundary of state change
   public void TestUpdateStateLoopPastBoundary() {
      Waldo waldo = new Waldo();
      waldo.setState(4);
      for (int i = 0; i < 150; i++) {
         waldo.updateState();
      }
      assertEquals(2, waldo.getState());
   }
}
