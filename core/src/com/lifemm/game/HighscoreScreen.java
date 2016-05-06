package com.lifemm.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.audio.Sound;

import java.util.List;

public class HighscoreScreen implements Screen {

   final LifeMM game;

   List<Score> highscores;

   // Fonts
   BitmapFont font;
  
   public HighscoreScreen (final LifeMM game) {
      this.game = game;
      highscores = ScoreAPI.getInstance().getScores();

      // Generate the font we use
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 46;
      parameter.borderColor = Color.BLACK;
      parameter.borderWidth = 2;
      font = generator.generateFont(parameter);
   }

   @Override
   public void render (float delta) {
      if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
         game.setScreen(new MainMenu(game));
         dispose();
      }
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      game.batch.begin();
      font.draw(game.batch, highscores.get(0).toString(), 1440/2 - 100, 700);
      font.draw(game.batch, highscores.get(1).toString(), 1440/2 - 100, 600);
      font.draw(game.batch, highscores.get(2).toString(), 1440/2 - 100, 500);
      font.draw(game.batch, highscores.get(3).toString(), 1440/2 - 100, 400);
      font.draw(game.batch, highscores.get(4).toString(), 1440/2 - 100, 300);
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