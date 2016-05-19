package com.lifemm.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainGame extends ScreenOverride {
   
   final LifeMM game;

   public enum MainGameState {PLAY, PAUSED, SELECT};

   // Main Character and Treasure
   Waldo waldo;
   Treasure treasure;

   // Levels
   Level level;

   // Fonts
   BitmapFont font;
  
   // List of entities that currently exist
   private List<Spider> enemies;
   private List<Crate> crates;
   private List<Cloud> clouds;

   // Textures for the background and floor
   private static Texture backgroundTexture;
   private static Texture floorTexture;

   // Textures for block menu
   private ArrayList<Texture> buttons;
   private ArrayList<Texture> selectedButtons;

   // Sounds
   private static Sound bgSound;

   // Constants
   private static final int FLOOR = 235;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;
   private static final int JUMPING = 3;
   private static final int BUILDING = 4;
   private static final int LEFT = -1;
   private static final int RIGHT = 1;
   
   // Globals
   private MainGameState state = MainGameState.PLAY;
   private int framesInState;
   private Stopwatch time;
   private int buttonSelection;
   private int maxButtons;
   private int delay;
   private Renderer renderer; 
    
   // Function that gets called once to prepare everything needed for render
   public MainGame (final LifeMM game) {
      this.game = game;
      buttons = new ArrayList<>();
      selectedButtons = new ArrayList<>();

      // One of a kind entities
      waldo = new Waldo();
      treasure = new Treasure();
      time = new Stopwatch();
      renderer = new Renderer();

      // Load Textures
      backgroundTexture = new Texture("bg4.png");
      floorTexture = new Texture("floor.png");
      // Load Button Textures for Block Selection Menu (Temporary Graphics)
      buttons.add(new Texture("crate.png"));
      buttons.add(new Texture("crate.png"));
      buttons.add(new Texture("playbutton.png")); 
      selectedButtons.add(new Texture("crate.png"));
      selectedButtons.add(new Texture("crate.png"));
      selectedButtons.add(new Texture("playbuttonselected.png"));

      // Load Sounds
      bgSound = Gdx.audio.newSound(Gdx.files.internal("data/bgSound.wav"));
      bgSound.play();

      // Generate the font we use
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 46;
      parameter.borderColor = Color.BLACK;
      parameter.borderWidth = 2;
      font = generator.generateFont(parameter);

      // Create lists of entities 
      crates = new ArrayList<>();
      clouds = new ArrayList<>();
      enemies = new ArrayList<>();
      enemies.add(new Spider());
      clouds.add(new Cloud(1, 300, -100));
      clouds.add(new Cloud(2, 0, 0));
      
      buttonSelection = 0;
      maxButtons = buttons.size();
      delay = 15;
      level = new Level();
    }

    @Override
    public void render (float delta) {
      if (state == MainGameState.PLAY) {
         doPlay(); 
      } else if (state == MainGameState.SELECT) {
	 doSelection();
      } else {
         doPaused();
      }
    }

   public void doPlay() {
      // Clears the screen, don't remove
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // Keep track of the number of frames that the
      // main character has spent in his current state
      waldo.updateStateTime();

      // Update how long the main character has been in
      // his current state for.
      waldo.updateState();

      // Accept user input
      updateWaldoMovement();
      boundsCheckWaldo();

      if (waldo.getHealth() <= 0) {
         waldo.decrementLives();
         waldo.setX(waldo.getLocation().x);
         waldo.setHealth(100);
      }

      if (waldo.getLives() == 0) {
         game.setScreen(new MainMenu(game));
         try {
            ScoreAPI.getInstance().saveScore("Player1", waldo.getScore());
         } catch(IOException e) {
            
         }
         dispose();
      }

      if (treasure.getHealth() <= 0) {
         game.setScreen(new MainMenu(game));
         try {
            ScoreAPI.getInstance().saveScore("Player1", waldo.getScore());
         } catch(IOException e) {

         }
         dispose();
      }

      waldo.update();

      framesInState++;

      if (framesInState > 20 && Gdx.input.isKeyPressed(Keys.B)) {
         state = MainGameState.SELECT;
         framesInState = 0;
      }

      if (framesInState > 20 && Gdx.input.isKeyPressed(Keys.P)) {
         state = MainGameState.PAUSED;
         framesInState = 0;
      }

      if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
         game.setScreen(new MainMenu(game));
         dispose();
      }

      if (Gdx.input.isKeyPressed(Keys.DEL)) {
         printDebugInfo();
      }

      updateCloudsPosition();


      // if the game is in a level pause,
      // then update the level state;
      if (level.isInLevelPause()) {
          level.updateLevelIfInPause();
       }

      // if the game is not in a level pause, update
      // the states of the current enemies
      if (!level.isInLevelPause()) { 
         updateEnemiesState();
      }

      game.batch.begin();
      renderBG();
      game.batch.draw(treasure.getCurrentTexture(), treasure.getLocation().x, treasure.getLocation().y);
      game.batch.draw(renderer.getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);

      font.draw(game.batch, "Health " + (int)waldo.getHealth(), 1100, 800);
      font.draw(game.batch, "Lives " + waldo.getLives(), 1100, 750);
      font.draw(game.batch, "Time " + (int)time.getTime(), 1100, 700);
      font.draw(game.batch, "Level " + level.getLevelNumber() , 600, 800);
      // if the game is in a level pause, then display the level number
      // at the center of the screen
      if (level.isInLevelPause())
      {
         font.draw(game.batch, "Level " + level.getLevelNumber(), 1440/2 - 100, 810/2);
      }
      font.draw(game.batch, "Score " + waldo.getScore(), 1100, 100);      

      time.lap();
      
      deleteDeadEnemies();
      deleteDeadCrates();
      renderCrates();

      // if the game is not in  a level pause, then render the enemies
      if (!level.isInLevelPause())
      {
         renderEnemies();
      }

      game.batch.end();
   }

   // Update the state and position of the main character based on user input
   public void updateWaldoMovement() {
    if (Gdx.input.isKeyPressed(Keys.UP) && waldo.getLocation().y == FLOOR) {
         waldo.setYVelocity(15);
         waldo.setYAcceleration(-1);
      }

      if (Gdx.input.isKeyPressed(Keys.SPACE) && waldo.getState() != ATTACKING) {
         waldo.setState(ATTACKING);
         for (Entity s : enemies) {
            if (isAttackCollision(waldo, s)) {
               s.setHealth(s.getHealth() - 300);
            }
            // Debug Output
            System.out.println(isAttackCollision(waldo, s));
         }
      }

      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
         waldo.setXVelocity(-4);
         waldo.setDirection(Entity.Direction.LEFT);
      }
      
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
         waldo.setXVelocity(4);
         waldo.setDirection(Entity.Direction.RIGHT);
      }

      if (!(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.LEFT))) {
         waldo.setXVelocity(0);
      }

      if (Gdx.input.isKeyPressed(Keys.A) && waldo.getState() != BUILDING) {
         Rectangle temp = new Rectangle(waldo.getLocation());
         if (waldo.getDirection() == Entity.Direction.LEFT) {
            temp.x -= 129;
         } else {
            temp.x += 129;
         }

         // Check for collisions
         for (int i = 0; i < crates.size(); i++) {
            if (isCollision(crates.get(i).getLocation(), temp)) {
               System.out.println("Can't place block here");
               return;
            }
         }

         if (isCollision(treasure.getLocation(), temp)) {
            System.out.println("Can't place block here");
            return;
         }

         waldo.setState(BUILDING);
         if (waldo.getDirection() == Entity.Direction.LEFT) {
            crates.add(new Crate(waldo.getLocation().x - 128));
         } else {
            crates.add(new Crate(waldo.getLocation().x + 128));
         }
      }
   }

   public void updateEnemiesState() {
      for (Enemy s : enemies) {
         s.updateStateTime();
         boolean move = true;
         for (Entity c : crates) {
            if (isCollision(s.getLocation(), c.getLocation())) {
               move = false;
               if (s.getState() != ATTACKING) {
                  s.setState(ATTACKING);
                  c.setHealth(c.getHealth() - s.getDamage());
               }
            }
         }
         if (isCollision(s.getLocation(), waldo.getLocation())) {
            move = false;
            if (s.getState() != ATTACKING) {
               s.setState(ATTACKING);
               waldo.setHealth(waldo.getHealth() - s.getDamage());
            }
         }
         if (isCollision(s.getLocation(), treasure.getLocation())) {
            move = false;
            if (s.getState() != ATTACKING) {
               s.setState(ATTACKING);
               treasure.setHealth(treasure.getHealth() - s.getDamage());
            }
         }
         if (move) {
            s.update();
         }
         if (s.getTimeInState() > s.getAttackInterval()) {
            s.setState(MOVING);
         }
      }
   }

   public void doSelection() {
      game.batch.begin();
      font.draw(game.batch, "Select a Block", 1440/2 - 200, 600);
      for (int ndx = 0; ndx < maxButtons; ndx++) {
	  game.batch.draw(buttons.get(ndx), 1440/2 - 100, 400 - 128 * ndx);
      }
      game.batch.draw(selectedButtons.get(buttonSelection), 1440/2 - 100, 400 - 128 * buttonSelection);
      if (Gdx.input.isKeyPressed(Keys.UP) && delay < 0) {
         if (buttonSelection > 0) {
            buttonSelection--;
         }
         delay = 15;
      }

      if (Gdx.input.isKeyPressed(Keys.DOWN) && delay < 0) {
         if (buttonSelection < maxButtons) {
            buttonSelection++;
         }
         delay = 15;
	 }

      if (Gdx.input.isKeyPressed(Keys.ENTER)) {
	 // need something to tell different blocks apart, a collection of them
         // whose indexes correspond to the same number as the button's
	 state = MainGameState.PLAY;
         framesInState = 0;
      }
      if (framesInState > 20 && Gdx.input.isKeyPressed(Keys.B)) {
         state = MainGameState.PLAY;
         framesInState = 0;
      }
      game.batch.end();
      framesInState++;
      delay--;
      
      time.restart();  
   }

   public void doPaused() {
      game.batch.begin();
      font.draw(game.batch, "Paused", 1440/2 - 100, 810/2);
      if (framesInState > 20 && Gdx.input.isKeyPressed(Keys.P)) {
         state = MainGameState.PLAY;
         framesInState = 0;
      }
      game.batch.end();
      framesInState++;
      
      time.restart();
   }

   public void renderBG() {
      game.batch.draw(backgroundTexture, 0, 0);
      game.batch.draw(floorTexture, 0, 0);
      for (int i = 0; i < clouds.size(); i++) {
         game.batch.draw(clouds.get(i).getCurrentTexture(), clouds.get(i).getLocation().x, clouds.get(i).getLocation().y);
         if (clouds.get(i).getLocation().x > 1800) {
            clouds.get(i).getLocation().x = -256;
         }
      }
   }

   void updateCloudsPosition() {
      Entity temp;
      for (int i = 0; i < clouds.size(); i++) {
         temp = clouds.get(i);
         temp.update();
      }
   }
   
   void printDebugInfo() {
      System.out.println("DEBUG INFO");
      System.out.println("--------------------------------------");
      System.out.println("Waldo State: " + waldo.getState());
      System.out.println("Waldo X: " + (waldo.getLocation().x + waldo.getLocation().width/2));
      System.out.println("Waldo Y: " + waldo.getLocation().y);
      System.out.println("Waldo Yvel: " + waldo.getYVelocity());
      System.out.println("Waldo Yacc: " + waldo.getYAcceleration());
      System.out.println("Waldo Xvel: " + waldo.getXVelocity());
      System.out.println("Waldo Xacc: " + waldo.getXAcceleration());
      System.out.println("Waldo Health: " + waldo.getHealth());
      System.out.println("Waldo Direction: " + waldo.getDirection());
      System.out.println("Treasure X: " + (treasure.getLocation().x + treasure.getLocation().width/2));
      System.out.println("Treasure Health: " + treasure.getHealth());
      System.out.println("--------------------------------------");
      System.out.println("");
   }

   // Check what enemies are alive
   public void deleteDeadEnemies() {
      for (int i = 0; i < enemies.size(); i++) {
         if (enemies.get(i).getHealth() <= 0) {
            //increment the number of enemies if you encounter an enemy that is dead
            level.incrementEnemiesKilled();
            enemies.remove(enemies.get(i));
            waldo.addScore(1000);
            // create a new ememy to appear on the left side of the game field
            if (level.getTotalEnemiesKilled()%2 == 1) {
               Spider leftSpider = new Spider(Entity.Direction.LEFT);
               enemies.add(leftSpider);
            }
            // create a new ememy to appear on the right side fo the game field
	    else {
               Spider rightSpider = new Spider(Entity.Direction.RIGHT);
               enemies.add(rightSpider);
            }
           // if all the enemies have been killed in the level,
           // then go to the next level
            if (level.AllEnemiesKilledInLevel()) {
                level.goToNextLevel();
           }
         }
      }
   }

   // Check what crates are alive
   public void deleteDeadCrates() {
      for (int i = 0; i < crates.size(); i++) {
         if (crates.get(i).getHealth() <= 0) {
            crates.remove(crates.get(i));
         }
      }
   }
   
   void boundsCheckWaldo() {
      if (waldo.getLocation().y < FLOOR) {
         waldo.setYAcceleration(0);
         waldo.setYVelocity(0);
         waldo.getLocation().y = FLOOR;
      }

      if (waldo.getLocation().x <= 0) {
         waldo.getLocation().x = 0;
      }

      if (waldo.getLocation().x >= 1440 - 128) {
         waldo.getLocation().x = 1440 - 128;
      }
   }

   public void renderEnemies() {
      for (Spider s : enemies) {
         if (s.getHealth() > 0) {
            game.batch.draw(s.getCurrentTexture(), s.getLocation().x, s.getLocation().y);
         }
      }
   }

   public void renderCrates() {
      for (int i = 0; i < crates.size(); i++) {
         game.batch.draw(crates.get(i).getCurrentTexture(), crates.get(i).getLocation().x, crates.get(i).getLocation().y);
      }
   }

   public boolean isCollision(Rectangle objectA, Rectangle objectB) {
      return (objectA.x <= (objectB.x + objectB.width)) && (objectB.x <= (objectA.x + objectA.width));
   }

   public boolean isAttackCollision(Waldo waldo, Entity entity) {
      float distance = (waldo.getX() + waldo.getWidth()/2) - (entity.getX() + entity.getWidth()/2);
 
      System.out.println("Distance away: " + distance);
     
      Entity.Direction requiredDirection;

      if (distance >= 0) {
         requiredDirection = Entity.Direction.LEFT;
      } else {
         requiredDirection = Entity.Direction.RIGHT;
      }

      return ((waldo.getDirection() == requiredDirection) && Math.abs(distance) < 150);
   }

   @Override
   public void dispose() {
      floorTexture.dispose();
      backgroundTexture.dispose();
      font.dispose();
      bgSound.dispose();
   }
}
