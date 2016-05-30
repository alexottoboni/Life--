package com.lifemm.game;

public class EnemyFactory {
   public Enemy getEnemy(String enemyType) {
      if (enemyType == null) {
         return null;
      }
      if (enemyType.equalsIgnoreCase("SPIDER")) {
         return new Spider();
      } else if (enemyType.equalsIgnoreCase("BEAR")) {
         return new Bear();
      } else if (enemyType.equalsIgnoreCase("BAT")) {
         return new Bat();
      }
      return null;
   }

   public Enemy getEnemy(String enemyType, Entity.Direction direction) {
      if (enemyType == null) {
         return null;
      }
      if (enemyType.equalsIgnoreCase("SPIDER")) {
         return new Spider(direction);
      } else if (enemyType.equalsIgnoreCase("BEAR")) {
         return new Bear(direction);
      } else if (enemyType.equalsIgnoreCase("BAT")) {
         return new Bat(direction);
      }
      return null;
   }
}
