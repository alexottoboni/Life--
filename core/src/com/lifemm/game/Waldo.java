package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Waldo {

   private static final int FLOOR = 235;
   private static final int LEFT = -1;
   private static final int RIGHT = 1;
   private static final int STARTING_HEALTH = 400;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;
   private static final int JUMPING = 3;
   private static final int BUILDING = 4;

   private int score;
   private int timeInState;
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
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;

      this.health = STARTING_HEALTH;
      this.direction = RIGHT;

      this.state = MOVING;

      this.leftTexture = new Texture("playersmall.png");
      this.rightTexture = new Texture("playersmallr.png"); 
      this.attackTexture = new Texture("playerswing.png");

   }

   public int getScore() {
      return this.score;
   }

   public Texture getCurrentTexture() {
      if (this.state == ATTACKING) {
         return this.leftTexture;
      } else if (this.state == MOVING) {
         if (this.direction == LEFT) {
            return this.leftTexture;
         } else if (this.direction == RIGHT) {
            return this.rightTexture;
         } else {
            return this.leftTexture;
         }
      } else if (this.state == JUMPING) {
         if (this.direction == LEFT) {
            return this.leftTexture;
         } else if (this.direction == RIGHT) {
            return this.rightTexture;
         } else {
            return this.leftTexture;
         }
      } else if (this.state == BUILDING) {
         if (this.direction == LEFT) {
            return this.leftTexture;
         } else if (this.direction == RIGHT) {
            return this.rightTexture;
         } else {
            return this.leftTexture;
         }
      } else {
         return this.leftTexture;
      }
   }

   public void updateStateTime() {
      this.timeInState++;
   }

   public void updateState() {
      if (this.state == JUMPING) {
         System.out.println(this.timeInState);
         if (this.timeInState < 15) {
            this.location.y += 4;
         } else if (this.timeInState >= 15 && this.timeInState < 30) {
            this.location.y -= 4;
         } else {
            this.location.y = FLOOR;
            this.setState(MOVING);
         }
      } else if (this.state == BUILDING) {
         if (this.timeInState > 100) {
            this.setState(MOVING);
         }       
      }
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      if (this.state == state) {
         return;
      } else {
         this.state = state;
         this.timeInState = 0;
      }
   }

   public void setLocationY(float y) {
      this.location.y = y;
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
