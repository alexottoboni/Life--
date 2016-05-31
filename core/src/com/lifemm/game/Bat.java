package com.lifemm.game;

public class Bat extends Enemy {

   Bat() {
      this.setHealth(100);
   }

   Bat(Entity.Direction direct) {
      super(direct);
      this.setHealth(100);
      this.attackInterval = 20;
      this.damage = 10;
   }
}
