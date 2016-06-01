package com.lifemm.game;

import com.badlogic.gdx.math.Rectangle;

public class Crate extends Entity {

   private static final int FLOOR = 235;
   private static final int STARTING_HEALTH = 50;

   Crate(float x) {
      this.location = new Rectangle();
      location.x = x;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;
      this.setHealth(STARTING_HEALTH);
   }
}
