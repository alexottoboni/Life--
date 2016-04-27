package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Waldo extends Entity {

   private static final int FLOOR = 235;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;
   private static final int JUMPING = 3;
   private static final int BUILDING = 4;

   private int timeInState;
   private int state;
   private int score;
   private Texture leftTexture;
   private Texture rightTexture;
   private Texture attackTexture;
   private int lives;
  
   Waldo() {
      location.x = 1440/2 - 128/2;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;

      this.direction = Entity.Direction.RIGHT;

      this.state = MOVING;

      this.leftTexture = new Texture("playersmall.png");
      this.rightTexture = new Texture("playersmallr.png"); 
      this.attackTexture = new Texture("playerswing.png");
      this.lives = 3;
   }

   public Texture getCurrentTexture() {
      if (this.state == ATTACKING) {
         return this.leftTexture;
      } else if (this.state == MOVING) {
         if (this.direction == Entity.Direction.LEFT) {
            return this.leftTexture;
         } else if (this.direction == Entity.Direction.RIGHT) {
            return this.rightTexture;
         } else {
            return this.leftTexture;
         }
      } else if (this.state == JUMPING) {
         if (this.direction == Entity.Direction.LEFT) {
            return this.leftTexture;
         } else if (this.direction == Entity.Direction.RIGHT) {
            return this.rightTexture;
         } else {
            return this.leftTexture;
         }
      } else if (this.state == BUILDING) {
         if (this.direction == Entity.Direction.LEFT) {
            return this.leftTexture;
         } else if (this.direction == Entity.Direction.RIGHT) {
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
      if (this.state == BUILDING) {
         if (this.timeInState > 100) {
            this.setState(MOVING);
         }       
      } else if (this.state == ATTACKING) {
         if (this.timeInState > 40) {
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

   public int getScore() {
      return score;
   }
  
   public int getLives() {
      return lives;
   }

}
