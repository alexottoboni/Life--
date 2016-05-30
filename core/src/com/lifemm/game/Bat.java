package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bat extends Enemy {

   private static final int MOVING = 2;

   private Texture texture;
   
   Bat() {
      location.x = 0;
      location.y = FLOOR + 180;
      location.width = 128;
      location.height = 128;

      this.direction = Entity.Direction.RIGHT;
      this.setXVelocity(1); 

      this.attackInterval = 50;
      this.damage = 20;
      this.timeInState = 0;
      this.state = MOVING;
      this.texture = new Texture("bat.png");
      this.setHealth(100);
   }

   Bat(Entity.Direction direct) {
      location.x = 0;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;

      this.direction = direct;

      this.attackInterval = 50;
      this.damage = 20;
      this.timeInState = 0;
      this.state = MOVING;
      if (direct == Entity.Direction.RIGHT)
      {
          this.texture = new Texture("bat.png");
          this.setXVelocity(1); 
      }
      else
      {
          this.texture = new Texture("batLeft.png");
          this.setXVelocity(-1);
          location.x = 1100;
      }
      this.setHealth(500);
   }

   public Texture getCurrentTexture() {
      return this.texture;
   }

   public void setLocationX(float x) {
      this.location.x = x;
   }

   public Rectangle getLocation() {
      return this.location;
   }

}
