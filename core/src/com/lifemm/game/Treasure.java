package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Treasure extends Entity {

   private static final int STARTING_HEALTH = 1500;
   private static final int FLOOR = 235;

   private Texture fullHealth;
   
   Treasure() {
      this.location = new Rectangle();
      location.x = 1440/2 - 256 / 2;
      location.y = FLOOR;
      location.width = 256;
      location.height = 256;
      setHealth(STARTING_HEALTH);
      this.fullHealth = new Texture("heart.png");
   }
   
   public Texture getCurrentTexture() {
      return this.fullHealth;
   }

}
