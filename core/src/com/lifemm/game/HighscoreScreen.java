package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.Color;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class HighscoreScreen extends ScreenOverride {

   final LifeMM game;
   private final static Logger LOGGER = Logger.getLogger(HighscoreScreen.class.getName()); 

   List<Score> highscores;

   // Fonts
   BitmapFont font;
  
   public HighscoreScreen (final LifeMM game) {
      // Generate the font we use
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 46;
      parameter.borderColor = Color.BLACK;
      parameter.borderWidth = 2;
      font = generator.generateFont(parameter);
      this.game = game;
      try {
        highscores = ScoreAPI.getInstance().getScores();
      } catch (IOException exception) {
        LOGGER.log(Level.SEVERE, "Failed to get highscores: ", exception);
        highscores = null;
      }
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
      if (highscores == null) {
        font.draw(game.batch, "Failed to get High Scores.", 1440/2 - 100, 700);
      } else {
        font.draw(game.batch, highscores.get(0).toString(), 1440/2 - 100, 700);
        font.draw(game.batch, highscores.get(1).toString(), 1440/2 - 100, 600);
        font.draw(game.batch, highscores.get(2).toString(), 1440/2 - 100, 500);
        font.draw(game.batch, highscores.get(3).toString(), 1440/2 - 100, 400);
        font.draw(game.batch, highscores.get(4).toString(), 1440/2 - 100, 300);
        game.batch.end();
      }
   }
}
