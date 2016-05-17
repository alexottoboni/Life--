package com.lifemm.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeMM extends Game {
   
   // OpenGL Draw Object
	SpriteBatch batch;

	@Override
	public void create () {
      batch = new SpriteBatch();
      this.setScreen(new MainMenu(this));
   }

   public void dispose() {
      batch.dispose();
   }
}
