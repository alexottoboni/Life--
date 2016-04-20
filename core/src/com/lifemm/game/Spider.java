package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Spider {

   private static final int FLOOR = 235;
   private static final int LEFT = -1;
   private static final int RIGHT = 1;
   private static final int STARTING_HEALTH = 100;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;

   private int timeInState;
   private int state;
   private int health;
   private int direction;
   private Rectangle location;
   private Texture rightTexture;
   
   Spider() {
      this.location = new Rectangle();
      location.x = 0;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;

      this.health = STARTING_HEALTH;
      this.direction = RIGHT;

      this.state = MOVING;
      this.rightTexture = new Texture("spider.png");
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

   public int getHealth() {
      return this.health;
   }
}
