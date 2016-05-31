package com.lifemm.game;

public class Spider extends Enemy {

   Spider() {
      this.setHealth(500);
   }

   Spider(Entity.Direction direct) {
      super(direct);
      this.setHealth(500);
      this.attackInterval = 50;
      this.damage = 20;
   }

}
