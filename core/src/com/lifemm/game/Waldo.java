package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;

public class Waldo extends Entity {

   private static final int FLOOR = 235;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;
   private static final int JUMPING = 3;
   private static final int BUILDING = 4;

   private int timeInState;
   private int state;
   private int score;
   private int lives;
   private Texture leftTexture;
   private Texture rightTexture;
   private Texture rightAttackTexture;
   private Texture leftAttackTexture;
  
   Waldo() {
      location.x = 1440/2 - 128/2;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;
      
      this.setHealth((float)100.00);
      this.direction = Entity.Direction.RIGHT;

      this.state = MOVING;

      this.leftTexture = new Texture("playersmall.png");
      this.rightTexture = new Texture("playersmallr.png"); 
      this.rightAttackTexture = new Texture("playerswing_right.png");
      this.leftAttackTexture = new Texture("playerswing_left.png");
      
      this.lives = 3;
   }

   public Texture getCurrentTexture() {
      if (this.state == ATTACKING) {
         if (this.direction == Entity.Direction.LEFT) {
            return this.leftAttackTexture;
         } else {
            return this.rightAttackTexture;
         }
      } else {
         if (this.direction == Entity.Direction.LEFT) {
            return this.leftTexture;
         } else {
            return this.rightTexture;
         }
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
      } else if (this.state == ATTACKING && this.timeInState > 40) {
         this.setState(MOVING);
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

   public void addScore(int points) {
      this.score += points;
   }
  
   public int getLives() {
      return lives;
   }
   
   public void decrementLives() {
      this.lives = lives - 1;
   }
}
