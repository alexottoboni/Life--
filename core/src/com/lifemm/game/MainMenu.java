package com.lifemm.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu implements Screen {

   final LifeMM game;

   BitmapFont titleFont;

   public static Texture backgroundTexture;

   public MainMenu (final LifeMM game) {
      this.game = game;
      backgroundTexture = new Texture("bg4.png");
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 72;
      parameter.borderWidth = 2;
      titleFont = generator.generateFont(parameter);
   } 

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      if (Gdx.input.isKeyPressed(Keys.ENTER)) {
         game.setScreen(new MainGame(game));
         dispose();
      }

      game.batch.begin();
      game.batch.draw(backgroundTexture, 0, 0);
      titleFont.draw(game.batch, "Life--", 1440/2, 700);
      titleFont.draw(game.batch, "Press Enter", 1440/2, 200);
      game.batch.end();
   }

   @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
