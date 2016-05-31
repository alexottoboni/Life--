package com.lifemm.game;

public class Bear extends Enemy {

   private static final int MOVING = 2;

   Bear() {
      this.setHealth(1000);
   }

   Bear(Entity.Direction direct) {
      this.setHealth(1000);
   }
}
