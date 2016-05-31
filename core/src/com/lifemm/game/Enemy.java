package com.lifemm.game;

import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy extends Entity {

   protected int attackInterval;
   protected int timeInState;
   protected int state;
   protected int damage;

   protected static final int MOVING = 2;

   Enemy() {
      location.x = 0;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;

      this.direction = Entity.Direction.RIGHT;
      this.setXVelocity(1);
      
      this.attackInterval = 50;
      this.damage = 20;
      this.timeInState = 0;
      this.state = MOVING;
   }

   Enemy(Entity.Direction direct) {
      location.x = 0;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;
      this.direction = direct;
      this.timeInState = 0;
      this.state = MOVING;
      if (direct == Entity.Direction.RIGHT)
      {
          this.setXVelocity(1); 
      }
      else
      {
          this.setXVelocity(-1);
          location.x = 1400;
      }
   }

   public void updateStateTime() {
      this.timeInState++;
   }

   public int getTimeInState() {
      return this.timeInState;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
      this.timeInState = 0;
   }

   public int getAttackInterval() {
      return this.attackInterval;
   }

   public int getDamage() {
      return this.damage;
   }

   public void setLocationX(float x) {
      this.location.x = x;
   }

   public Rectangle getLocation() {
      return this.location;
   }
}
