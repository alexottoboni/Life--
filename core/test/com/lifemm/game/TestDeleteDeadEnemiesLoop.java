package com.lifemm.game;

import org.junit.Test;
import com.lifemm.game.EnemyList;
import junit.framework.TestCase;

/**
 * Tests the Enemy objects being destroyed loop
 *
 */

public class TestDeleteDeadEnemiesLoop extends TestCase {

   public void testDeleteEmpty() {
      EnemyList enemyList = new EnemyList();
      enemyList.deleteDeadEnemies();
      assertEquals(0, enemyList.size());
   }

   public void testDeleteOne() {
      EnemyList enemyList = new EnemyList();
      Enemy enemy = new Spider();
      enemy.setHealth(0);
      enemyList.add(enemy);
      enemyList.deleteDeadEnemies();
      assertEquals(0, enemyList.size());
   }

   public void testDeleteTwo() {
      EnemyList enemyList = new EnemyList();
      Enemy enemy = new Spider();
      Enemy enemy1 = new Spider();
      enemy.setHealth(0);
      enemyList.add(enemy);
      enemyList.add(enemy1);
      enemyList.deleteDeadEnemies();
      assertEquals(1, enemyList.size());
   }

   public void testDeleteTen() {
      EnemyList enemyList = new EnemyList();
      Enemy enemy0 = new Spider();
      Enemy enemy1 = new Spider();
      Enemy enemy2 = new Spider();
      Enemy enemy3 = new Spider();
      Enemy enemy4 = new Spider();
      Enemy enemy5 = new Spider();
      Enemy enemy6 = new Spider();
      Enemy enemy7 = new Spider();
      Enemy enemy8 = new Spider();
      Enemy enemy9 = new Spider();
      enemyList.add(enemy0);
      enemyList.add(enemy1);
      enemyList.add(enemy2);
      enemyList.add(enemy3);
      enemyList.add(enemy4);
      enemyList.add(enemy5);
      enemyList.add(enemy6);
      enemyList.add(enemy7);
      enemyList.add(enemy8);
      enemyList.add(enemy9);
      enemyList.deleteDeadEnemies();
      assertEquals(10, enemyList.size());
   }


}
