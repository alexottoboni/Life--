package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Waldo {

   private static final int LEFT = -1;
   private static final int RIGHT = 1;
   private static final int STARTING_HEALTH = 400;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;
   

   private int state;
   private int health;
   private int direction;
   private Rectangle location;
   private Texture leftTexture;
   private Texture rightTexture;
   private Texture attackTexture;
  
   Waldo() {
      this.location = new Rectangle();
      location.x = 1440/2 - 128/2;
      location.y = 0;
      location.width = 64;
      location.height = 64;

      this.health = STARTING_HEALTH;
      this.direction = RIGHT;

      this.state = MOVING;

      this.leftTexture = new Texture("playersmall.png");
      this.rightTexture = new Texture("playersmallr.png"); 
      this.attackTexture = new Texture("playerswing.png");

   }

   public Texture getCurrentTexture() {
      if (this.state == ATTACKING) {

      } else if (this.state == MOVING) {

      } else {
         return this.leftTexture;
      }
      if (this.direction == LEFT) {
         return this.leftTexture;
      } else if (this.direction == RIGHT) {
         return this.rightTexture;
      } else {
         return this.leftTexture;
      }
   }

   public void setLocationX(float x) {
      this.location.x = x;
   }

   public Rectangle getLocation() {
      return this.location;
   }

   public void setDirection(int direction) {
      this.direction = direction;
   }

   public int getDirection() {
      return this.direction;
   }

   public int getHealth() {
      return this.health;
   }
 
}
