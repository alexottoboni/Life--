package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bat extends Enemy {

   private static final int MOVING = 2;

   Bat() {
      this.setHealth(100);
      this.setHealth(100);
   }

   Bat(Entity.Direction direct) {
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
