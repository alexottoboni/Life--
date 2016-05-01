package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Spider extends Entity {

   private static final int FLOOR = 235;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;

   private int timeInState;
   private int state;
   private Texture rightTexture;
   
   Spider() {
      location.x = 0;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;

      this.direction = Entity.Direction.RIGHT;
      this.setXVelocity(1); 

      this.state = MOVING;
      this.rightTexture = new Texture("spider.png");
      this.setHealth(500);
   }

   public Texture getCurrentTexture() {
      return this.rightTexture;
   }

   public void updateStateTime() {
      this.timeInState++;
   }

   public int getState() {
      return this.state;
   }
   
   public void setLocationX(float x) {
      this.location.x = x;
   }

   public Rectangle getLocation() {
      return this.location;
   }

}
