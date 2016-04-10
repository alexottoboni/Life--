package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cloud {

   private static final int STARTING_HEALTH = 50;
   
   private int speed;
   private int health;
   private Rectangle location;
   private Texture fullHealthTexture;

   Cloud(int texNum, int offsetX, int offsetY) {
      this.location = new Rectangle();
      location.x = 0 + offsetX;
      location.y = 500 + offsetY;
      location.width = 256;
      location.height = 256;
      this.health = STARTING_HEALTH;
      this.speed = (int)(Math.random() * (2 - 1) + 1);
      this.fullHealthTexture = new Texture("cloud" + texNum + ".png");
   }

   public int getSpeed() {
      return this.speed;
   }

   public void setLocationX(float x) {
      this.location.x = x;
   }

   public Rectangle getLocation() {
      return this.location;
   }

   public Texture getCurrentTexture() {
      return this.fullHealthTexture;
   }
}
