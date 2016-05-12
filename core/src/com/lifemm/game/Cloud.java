package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Cloud extends Entity{

   private Texture fullHealthTexture;

   Cloud(int texNum, int offsetX, int offsetY) {
      this.location = new Rectangle();
      location.x = 0 + offsetX;
      location.y = 500 + offsetY;
      location.width = 256;
      location.height = 256;
      this.setXVelocity((int)(Math.random() * (2 - 1) + 1));
      this.fullHealthTexture = new Texture("cloud" + texNum + ".png");
   }

   public Texture getCurrentTexture() {
      return this.fullHealthTexture;
   }
}
