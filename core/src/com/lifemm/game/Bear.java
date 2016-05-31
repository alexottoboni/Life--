package com.lifemm.game;

public class Bear extends Enemy {

   Bear() {
      this.setHealth(1000);
      this.attackInterval = 100;
      this.damage = 50;
   }

   Bear(Entity.Direction direct) {
      super(direct);
      this.setHealth(1000);
      this.attackInterval = 100;
      this.damage = 50;
   }
}
