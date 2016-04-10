package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Treasure {

   private static final int STARTING_HEALTH = 1500;
   private static final int FLOOR = 235;

   private int health;
   private Rectangle location;
   private Texture fullHealth;
   
   Treasure() {
      this.location = new Rectangle();
      location.x = 1440/2 - 256 / 2;
      location.y = FLOOR;
      location.width = 256;
      location.height = 256;
      this.health = STARTING_HEALTH;
      this.fullHealth = new Texture("heart.png");
   }
   
   public int getHealth() {
      return this.health;
   }

   public Texture getCurrentTexture() {
      return this.fullHealth;
   }

   public Rectangle getLocation() {
      return this.location;
   }

}
