package com.lifemm.game;

public class EnemyFactory {
   public Enemy getEnemy(String enemyType) {
      if ("SPIDER".equalsIgnoreCase(enemyType)) {
         return new Spider();
      } else if ("BEAR".equalsIgnoreCase(enemyType)) {
         return new Bear();
      } else if ("BAT".equalsIgnoreCase(enemyType)) {
         return new Bat();
      }
      return null;
   }

   public Enemy getEnemy(String enemyType, Entity.Direction direction) {
      if ("SPIDER".equalsIgnoreCase(enemyType)) {
         return new Spider(direction);
      } else if ("BEAR".equalsIgnoreCase(enemyType)) {
         return new Bear(direction);
      } else if ("BAT".equalsIgnoreCase(enemyType)) {
         return new Bat(direction);
      }
      return null;
   }
}
