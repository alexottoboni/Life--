package com.lifemm.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class MainMenu extends ScreenOverride {

   final LifeMM game;

   BitmapFont titleFont;
   private static Texture backgroundTexture;
   private static Texture play;
   private static Texture selectedPlay;
   private static Texture tutorial;
   private static Texture selectedTutorial;
   private static Texture highscores;
   private static Texture selectedHighscores;
   private int buttonSelection;

   // Sounds
   private static Sound bgSound;

   int delaySelection;

   public MainMenu (final LifeMM game) {
      this.game = game;
      this.delaySelection = 15;
      this.buttonSelection = 0;
      selectedPlay = new Texture("playbuttonselected.png");
      play = new Texture("playbutton.png");
      tutorial = new Texture("tutorialbutton.png");
      selectedTutorial = new Texture("tutorialbuttonselected.png");
      highscores = new Texture("highscorebutton.png");
      selectedHighscores = new Texture("highscorebuttonselected.png");
      backgroundTexture = new Texture("bg4.png");
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 72;
      parameter.borderWidth = 2;
      titleFont = generator.generateFont(parameter);
      bgSound = Gdx.audio.newSound(Gdx.files.internal("data/menuSound.wav"));
      bgSound.play();
   } 

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      if (Gdx.input.isKeyPressed(Keys.ENTER)) {
         switch(buttonSelection) {
            case 0:
               game.setScreen(new MainGame(game));
               dispose();
               break;
            case 1:
               game.setScreen(new HighscoreScreen(game));
               break;
            case 2:
               game.setScreen(new Tutorial(game));
               break;
            default:
               throw new Error("Unknown button: " + buttonSelection);
         }
         dispose();
      }

      moveIndex();

      game.batch.begin();
      game.batch.draw(backgroundTexture, 0, 0);
      titleFont.draw(game.batch, "Life--", 1440/2 - 100, 700);
      titleFont.draw(game.batch, "Press Enter", 1440/2 - 200, 200);
      drawButtons();
      game.batch.end();
      delaySelection--;
   }

   private void moveIndex() {
      if (Gdx.input.isKeyPressed(Keys.UP) && delaySelection < 0) {
         if (buttonSelection > 0) {
            buttonSelection--;
         }
         delaySelection = 15;
      }

      if (Gdx.input.isKeyPressed(Keys.DOWN) && delaySelection < 0) {
         if (buttonSelection < 2) {
            buttonSelection++;
         }
         delaySelection = 15;
      }
   }

   void drawButtons() {
      if (buttonSelection == 0) {
         game.batch.draw(selectedPlay, 1440/2 - 128, 400);
      } else {
         game.batch.draw(play, 1440/2 - 128, 400);
      }

      if (buttonSelection == 1) {
         game.batch.draw(selectedHighscores, 1440/2 - 128, 350);
      } else {
         game.batch.draw(highscores, 1440/2 - 128, 350);
      }

      if (buttonSelection == 2) {
         game.batch.draw(selectedTutorial, 1440/2 - 128, 300);
      } else {
         game.batch.draw(tutorial, 1440/2 - 128, 300);
      }

   }

    @Override
    public void dispose() {
      bgSound.dispose();
    }
}
