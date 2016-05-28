/* TestLevel.java
 * 
 * Tests the Level class.
 *
 * Written by Jerrick Fong
 */

package com.lifemm.game;

import org.junit.*;
import com.lifemm.game.Level;
import static org.junit.Assert.*;

public class TestLevel {
   @Test
   public void testIsInLevelPauseFalse() {
      Level level = new Level();
      
      assertFalse(level.isInLevelPause());
   }
   
   @Test
   public void testIsInLevelPauseTrue() {
      Level level = new Level();
      
      level.goToNextLevel();
      
      assertTrue(level.isInLevelPause());
   }
   
   @Test
   public void testGetTotalEnemiesKilled() {
      Level level = new Level();
      
      assertEquals(0, level.getTotalEnemiesKilled());
   }
   
   @Test
   public void testIncrementEnemiesKilled() {
      Level level = new Level();
      
      level.incrementEnemiesKilled();
      
      assertEquals(1, level.getTotalEnemiesKilled());
   }
   
   @Test
   public void testAllEnemiesKilledInLevelFalse() {
      Level level = new Level();
      
      assertFalse(level.allEnemiesKilledInLevel());
   }
   
   @Test
   public void testAllEnemiesKilledInLevelTrue() {
      Level level = new Level();
      
      level.incrementEnemiesKilled();
      level.incrementEnemiesKilled();      
      
      assertTrue(level.allEnemiesKilledInLevel());
   }
   
   @Test
   public void testGoToNextLevel() {
      Level level = new Level();
      
      level.incrementEnemiesKilled();
      level.incrementEnemiesKilled();
      level.goToNextLevel();   
      level.incrementEnemiesKilled();
      level.incrementEnemiesKilled();   
      
      assertFalse(level.allEnemiesKilledInLevel());
   }
   
   @Test
   public void testGetLevelNumber() {
      Level level = new Level();
      
      level.goToNextLevel(); 
      
      assertEquals(2, level.getLevelNumber());
   }
}