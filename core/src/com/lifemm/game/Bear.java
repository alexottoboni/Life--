package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bear extends Enemy {

   private static final int MOVING = 2;

   Bear() {
      this.setHealth(1000);
   }

   Bear(Entity.Direction direct) {
      if (direct == Entity.Direction.RIGHT)
      if (direct == Entity.Direction.RIGHT)
      {
          this.setXVelocity(1); 
      }
      else
      {
          this.setXVelocity(-1);
          location.x = 1400;
      }
      this.setHealth(500);
   }

   public void setLocationX(float x) {
      this.location.x = x;
   }

   public Rectangle getLocation() {
      return this.location;
   }
}
