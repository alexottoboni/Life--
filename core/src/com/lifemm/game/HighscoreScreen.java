package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Screen;

import java.io.IOException;
import java.util.List;

public class HighscoreScreen implements Screen {

   final LifeMM game;

   List<Score> highscores;

   // Fonts
   BitmapFont font;
  
   public HighscoreScreen (final LifeMM game) {
      this.game = game;
      try {
         highscores = ScoreAPI.getInstance().getScores();
      } catch (IOException e) {

      }
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
        // Method must be overridden to implement Screen
    }

    @Override
    public void show() {
        // Method must be overridden to implement Screen
    }

    @Override
    public void hide() {
        // Method must be overridden to implement Screen
    }

    @Override
    public void pause() {
        // Method must be overridden to implement Screen
    }

    @Override
    public void resume() {
        // Method must be overridden to implement Screen
    }

    @Override
    public void dispose() {
        // Method must be overridden to implement Screen
    }
}
