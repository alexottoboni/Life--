package com.lifemm.game;

import com.badlogic.gdx.math.Rectangle;

public class Treasure extends Entity {

   private static final int STARTING_HEALTH = 1500;
   private static final int FLOOR = 235;
   
   Treasure() {
      this.location = new Rectangle();
      location.x = 1440/2 - 256 / 2;
      location.y = FLOOR;
      location.width = 256;
      location.height = 256;
      setHealth(STARTING_HEALTH);
   }

}
