package com.lifemm.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;

public class LifeMM extends ApplicationAdapter {
	SpriteBatch batch;
   Waldo waldo;
   Treasure treasure;

   public static Texture backgroundTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
      waldo = new Waldo();
      treasure = new Treasure();
      backgroundTexture = new Texture("bg.jpg");
	}

   public void renderBG() {
      batch.draw(backgroundTexture, 0, 0);
   }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      if (Gdx.input.isKeyPressed(Keys.SPACE)) {
         System.out.println(isAttackCollision(waldo.getLocation(), treasure.getLocation()));
      }

      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
         waldo.setLocationX(waldo.getLocation().x - 300 * Gdx.graphics.getDeltaTime());
         waldo.setDirection(-1);
      }
      
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
         waldo.setLocationX(waldo.getLocation().x + 300 * Gdx.graphics.getDeltaTime());
         waldo.setDirection(1);
      }

      if (waldo.getLocation().x < 0) {
         waldo.setLocationX(0);
      }

      if (waldo.getLocation().x > 1440 - 128) {
         waldo.setLocationX(1440 - 128);
      }

      if (Gdx.input.isKeyPressed(Keys.DEL)) {
         System.out.println("DEBUG INFO");
         System.out.println("--------------------------------------");
         System.out.println("Waldo X: " + (waldo.getLocation().x + waldo.getLocation().width/2));
         System.out.println("Waldo Y: " + waldo.getLocation().y);
         System.out.println("Waldo Health: " + waldo.getHealth());
         System.out.println("Waldo Direction: " + waldo.getDirection());
         System.out.println("Treasure X: " + (treasure.getLocation().x + treasure.getLocation().width/2));
         System.out.println("Treasure Health: " + treasure.getHealth());
         System.out.println("--------------------------------------");
         System.out.println("");
      }

		batch.begin();
      renderBG();
      batch.draw(treasure.getCurrentTexture(), treasure.getLocation().x, treasure.getLocation().y);
		batch.draw(waldo.getCurrentTexture(), waldo.getLocation().x, waldo.getLocation().y);
		batch.end();

   }

   public boolean isAttackCollision(Rectangle player, Rectangle object) {
      if (Math.abs((player.x + player.width/2) - (object.x + object.width/2)) < 150) {
         return true;
      } else {
         return false;
      }
   }
}
