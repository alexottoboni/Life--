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
   public static Texture selectedPlay;
   public static Texture play;
   public static Texture selectedControls;
   public static Texture controls;
   public int buttonSelection;

   public MainMenu (final LifeMM game) {
      this.game = game;
      this.buttonSelection = 0;
      selectedPlay = new Texture("playbuttonselected.png");
      play = new Texture("playbutton.png");
      controls = new Texture("controlsbutton.png");
      selectedControls = new Texture("controlsbuttonselected.png");
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
         if (buttonSelection == 0) {
            game.setScreen(new MainGame(game));
            dispose();
         } else if (buttonSelection == 1) {

         } else {

         }
      }

      if (Gdx.input.isKeyPressed(Keys.UP)) {
         if (buttonSelection > 0) {
            buttonSelection--;
         }
      }

      if (Gdx.input.isKeyPressed(Keys.DOWN)) {
         if (buttonSelection < 1) {
            buttonSelection++;
         }
      }

      game.batch.begin();
      game.batch.draw(backgroundTexture, 0, 0);
      titleFont.draw(game.batch, "Life--", 1440/2 - 100, 700);
      titleFont.draw(game.batch, "Press Enter", 1440/2 - 100, 200);
      drawButtons();
      game.batch.end();
   }

   void drawButtons() {
      if (buttonSelection == 0) {
         game.batch.draw(selectedPlay, 1440/2 - 128, 400);
      } else {
         game.batch.draw(play, 1440/2 - 128, 400);
      }

      if (buttonSelection == 1) {
         game.batch.draw(selectedControls, 1440/2 - 128, 350);
      } else {
         game.batch.draw(controls, 1440/2 - 128, 350);
      }

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
