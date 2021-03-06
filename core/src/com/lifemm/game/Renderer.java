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
   private Texture spider;
   private Texture spiderLeft;
   private Texture bear;
   private Texture bearLeft;
   private Texture bat;
   private Texture batLeft;
   private Texture crate;
   private static Renderer instance;

   private Renderer() {
      this.leftTexture = new Texture("playersmall.png");
      this.rightTexture = new Texture("playersmallr.png"); 
      this.rightAttackTexture = new Texture("playerswing_right.png");
      this.leftAttackTexture = new Texture("playerswing_left.png");
      this.treasure = new Texture("heart.png");
      this.spider = new Texture("spider.png");
      this.spiderLeft = new Texture("spiderLeft.png");
      this.bear = new Texture("bear.png");
      this.bearLeft = new Texture("bearLeft.png");
      this.bat = new Texture("bat.png");
      this.batLeft = new Texture("batLeft.png");
      this.crate = new Texture("crate.png");
   }

   public static Renderer getInstance() {
      if (instance == null) {
         instance = new Renderer();
      }
      return instance;
   }

   public Texture getCurrentTexture(Enemy enemy) {
      if (enemy instanceof Spider) {
         return isSpider(enemy);
      } else if (enemy instanceof Bear) {
         return isBear(enemy);
      } else if (enemy instanceof Bat) {
         return isBat(enemy);
      }
      return null;
   }

   public Texture isSpider(Enemy enemy) {
      if (enemy.getDirection() == Entity.Direction.RIGHT) {
         return this.spider;
      } else {
         return this.spiderLeft;
      }
   }

   public Texture isBat(Enemy enemy) {
      if (enemy.getDirection() == Entity.Direction.RIGHT) {
         return this.bat;
      } else {
         return this.batLeft;
      }
   }

   public Texture isBear(Enemy enemy) {
      if (enemy.getDirection() == Entity.Direction.RIGHT) {
         return this.bear;
      } else {
         return this.bearLeft;
      }
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

   public Texture getCrateTexture() {
      return this.crate;
   }
}
