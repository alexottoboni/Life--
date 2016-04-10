package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Crate {

   private static final int FLOOR = 235;
   private static final int STARTING_HEALTH = 50;
   
   private int health;
   private Rectangle location;
   private Texture fullHealthTexture;

   Crate(float x) {
      this.location = new Rectangle();
      location.x = x;
      location.y = FLOOR;
      location.width = 128;
      location.height = 128;
      this.health = STARTING_HEALTH;
      this.fullHealthTexture = new Texture("crate.png");
   }

   public Rectangle getLocation() {
      return this.location;
   }

   public Texture getCurrentTexture() {
      return this.fullHealthTexture;
   }
}
