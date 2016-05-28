package com.lifemm.game;

import com.badlogic.gdx.graphics.Texture;
import com.lifemm.game.Entity;

public class Renderer {

   private static final int ATTACKING = 1;

   // Textures for Waldo
   private Texture leftTexture;
   private Texture rightTexture;
   private Texture rightAttackTexture;
   private Texture leftAttackTexture;
   private Texture treasure;

   private static Renderer instance;

   private Renderer() {
      this.leftTexture = new Texture("playersmall.png");
      this.rightTexture = new Texture("playersmallr.png"); 
      this.rightAttackTexture = new Texture("playerswing_right.png");
      this.leftAttackTexture = new Texture("playerswing_left.png");
      this.treasure = new Texture("heart.png");
   }

   public static Renderer getInstance() {
      if (instance == null) {
         instance = new Renderer();
      }
      return instance;
   }

   public Texture getCurrentTexture(Waldo waldo) {
      int state = waldo.getState();
      Entity.Direction direction = waldo.getDirection();
      if (state == ATTACKING) {
         if (direction == Entity.Direction.LEFT) {
            return this.leftAttackTexture;
         } else {
            return this.rightAttackTexture;
         }
      } else {
         if (direction == Entity.Direction.LEFT) {
            return this.leftTexture;
         } else {
            return this.rightTexture;
         }
      }
   }

   public Texture getTreasureTexture() {
      return this.treasure;
   }
}
