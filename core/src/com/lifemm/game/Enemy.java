package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy extends Entity {

   protected int attackInterval;
   protected int timeInState;
   protected int state;
   protected int damage;

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

}
