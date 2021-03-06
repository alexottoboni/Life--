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
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Random;

public class MainGame extends ScreenOverride {
   
   final LifeMM game;
   private static final Logger LOGGER = Logger.getLogger(MainGame.class.getName()); 

   public enum MainGameState {PLAY, PAUSED, SELECT};

   // Main Character and Treasure
   private Waldo waldo;
   Treasure treasure;

   // Levels
   com.lifemm.game.Level level;

   // Fonts
   BitmapFont font;
  
   // List of entities that currently exist
   private List<Enemy> enemies;
   private CrateList crates;
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
   private static final int BUILDING = 4;
   private static final String CRATE_IMG_LOC = "crate.png";  
 
   // Globals
   private MainGameState state = MainGameState.PLAY;
   private int framesInState;
   private Stopwatch time;
   private int buttonSelection;
   private int maxButtons;
   private int delay;
   private EnemyFactory factory;
    
   // Function that gets called once to prepare everything needed for render
   public MainGame (final LifeMM game) {
      this.game = game;
      buttons = new ArrayList<>();
      selectedButtons = new ArrayList<>();

      // One of a kind entities
      waldo = new Waldo();
      treasure = new Treasure();
      time = new Stopwatch();
      factory = new EnemyFactory();

      // Load Textures
      backgroundTexture = new Texture("bg4.png");
      floorTexture = new Texture("floor.png");
      // Load Button Textures for Block Selection Menu (Temporary Graphics)
      buttons.add(new Texture(CRATE_IMG_LOC));
      buttons.add(new Texture(CRATE_IMG_LOC));
      buttons.add(new Texture("playbutton.png")); 
      selectedButtons.add(new Texture(CRATE_IMG_LOC));
      selectedButtons.add(new Texture(CRATE_IMG_LOC));
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
      crates = new CrateList();
      clouds = new ArrayList<>();
      enemies = new ArrayList<>();
      enemies.add(factory.getEnemy("Spider"));
      clouds.add(new Cloud(1, 300, -100));
      clouds.add(new Cloud(2, 0, 0));
      
      buttonSelection = 0;
      maxButtons = buttons.size();
      delay = 15;
      level = new com.lifemm.game.Level();
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

   public void checkEndGame() {
      if (waldo.getHealth() <= 0) {
         waldo.decrementLives();
         waldo.setX(waldo.getLocation().x);
         waldo.setHealth(100);
      }

      if (waldo.getLives() == 0) {
         game.setScreen(new MainMenu(game));
         try {
            ScoreAPI.getInstance().saveScore("Player1", waldo.getScore());
         } catch(IOException exception) {
            LOGGER.log(Level.SEVERE, "Failed to save highscore: ", exception);
         }
         dispose();
      }

      if (treasure.getHealth() <= 0) {
         game.setScreen(new MainMenu(game));
         try {
            ScoreAPI.getInstance().saveScore("Player1", waldo.getScore());
         } catch(IOException exception) {
            LOGGER.log(Level.SEVERE, "Failed to save highscore: ", exception);
         }
         dispose();
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
      
      checkEndGame();
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


      if (level.isInLevelPause()) {
          level.updateLevelIfInPause();
       }

      if (!level.isInLevelPause()) { 
         updateEnemiesState();
      }

      game.batch.begin();
      renderBG();
      game.batch.draw(Renderer.getInstance().getTreasureTexture(), treasure.getLocation().x, treasure.getLocation().y);
      game.batch.draw(Renderer.getInstance().getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);

      font.draw(game.batch, "Health " + (int)waldo.getHealth(), 1100, 800);
      font.draw(game.batch, "Lives " + waldo.getLives(), 1100, 750);
      font.draw(game.batch, "Time " + (int)time.getTime(), 1100, 700);
      font.draw(game.batch, "Level " + level.getLevelNumber() , 600, 800);
      font.draw(game.batch, "Score " + waldo.getScore(), 1100, 100);      

      checkPause();

      time.lap();
      
      deleteDeadEnemies();
      crates.deleteDeadCrates();
      renderCrates();

      // if the game is not in  a level pause, then render the enemies
      if (!level.isInLevelPause())
      {
         renderEnemies();
      }

      game.batch.end();
   }

   public void checkPause() {
      // if the game is in a level pause, then display the level number
      // at the center of the screen
      if (level.isInLevelPause())
      {
         font.draw(game.batch, "Level " + level.getLevelNumber(), 1440/2 - 100, 810/2);
      }
   }

   public void updateWaldoAttack() {
      if (Gdx.input.isKeyPressed(Keys.SPACE) && waldo.getState() != ATTACKING) {
         waldo.setState(ATTACKING);
         for (Entity s : enemies) {
            if (isAttackCollision(waldo, s)) {
               s.setHealth(s.getHealth() - 300);
            }
            // Debug Output
            LOGGER.log(Level.INFO, Boolean.toString(isAttackCollision(waldo, s)));
         }
      }
   }

   public void updateWaldoJump() {
       if (Gdx.input.isKeyPressed(Keys.UP) && waldo.getLocation().y == FLOOR) {
         waldo.setYVelocity(15);
         waldo.setYAcceleration(-1);
       }
   }

   public void updateWaldoLeftRight() {
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

   }

   // Update the state and position of the main character based on user input
   public void updateWaldoMovement() {

      updateWaldoJump();
      updateWaldoAttack();
      updateWaldoLeftRight();
   
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
               LOGGER.log(Level.INFO, "Can't place block here");
               return;
            }
         }

         if (isCollision(treasure.getLocation(), temp)) {
            LOGGER.log(Level.INFO, "Can't place block here");
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

   public boolean checkEnemyCollisions(Enemy s) {
         for (Crate c : crates.getIterable()) {
            if (isCollision(s.getLocation(), c.getLocation())) {
               if (s.getState() != ATTACKING) {
                  s.setState(ATTACKING);
                  c.setHealth(c.getHealth() - s.getDamage());
               }
               return false;
            }
         }
         return true;
   }

   public void updateEnemiesState() {
      for (Enemy s : enemies) {
         s.updateStateTime();
         boolean move = checkEnemyCollisions(s);
         if (isCollision(s.getLocation(), waldo.getLocation())) {
            if (s.getState() != ATTACKING) {
               s.setState(ATTACKING);
               waldo.setHealth(waldo.getHealth() - s.getDamage());
            }
         }
         else if (isCollision(s.getLocation(), treasure.getLocation())) {
            if (s.getState() != ATTACKING) {
               s.setState(ATTACKING);
               treasure.setHealth(treasure.getHealth() - s.getDamage());
            }
         } else if (move) {
            s.update();
         }
         
         if (s.getTimeInState() > s.getAttackInterval()) {
            s.setState(MOVING);
         }
      }
   }

   public void checkSelectionExit() {
      if (Gdx.input.isKeyPressed(Keys.ENTER)) {
	      state = MainGameState.PLAY;
         framesInState = 0;
      }
      else if (framesInState > 20 && Gdx.input.isKeyPressed(Keys.B)) {
         state = MainGameState.PLAY;
         framesInState = 0;
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
      else if (Gdx.input.isKeyPressed(Keys.DOWN) && delay < 0) {
         if (buttonSelection < maxButtons) {
            buttonSelection++;
         }
         delay = 15;
	   }
      checkSelectionExit();
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

      LOGGER.log(Level.INFO, "DEBUG INFO");
      LOGGER.log(Level.INFO, "--------------------------------------");
      LOGGER.log(Level.INFO, "Waldo State: " + waldo.getState());
      LOGGER.log(Level.INFO, "Waldo X: " + (waldo.getLocation().x + waldo.getLocation().width/2));
      LOGGER.log(Level.INFO, "Waldo Y: " + waldo.getLocation().y);
      LOGGER.log(Level.INFO, "Waldo Yvel: " + waldo.getYVelocity());
      LOGGER.log(Level.INFO, "Waldo Yacc: " + waldo.getYAcceleration());
      LOGGER.log(Level.INFO, "Waldo Xvel: " + waldo.getXVelocity());
      LOGGER.log(Level.INFO, "Waldo Xacc: " + waldo.getXAcceleration());
      LOGGER.log(Level.INFO, "Waldo Health: " + waldo.getHealth());
      LOGGER.log(Level.INFO, "Waldo Direction: " + waldo.getDirection());
      LOGGER.log(Level.INFO, "Treasure X: " + (treasure.getLocation().x + treasure.getLocation().width/2));
      LOGGER.log(Level.INFO, "Treasure Health: " + treasure.getHealth());
      LOGGER.log(Level.INFO, "--------------------------------------");
      LOGGER.log(Level.INFO, "");
   }

   public Enemy getNewEnemy(int direction) {
      Random generator = new Random();
      Entity.Direction newDir = Entity.Direction.RIGHT;
      if (direction == 1) {
         newDir = Entity.Direction.LEFT;
      }
      int selection = generator.nextInt(3) + 1;
      if (selection == 1) {
         return factory.getEnemy("Bear", newDir);
      } else if (selection == 2) {
         return factory.getEnemy("Bat", newDir);
      } else if (selection == 3) {
         return factory.getEnemy("Spider", newDir);
      }
      return null;
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
               Enemy leftEnemy = getNewEnemy(0);
               enemies.add(leftEnemy);
            }
            // create a new ememy to appear on the right side fo the game field
	    else {
               Enemy rightEnemy = getNewEnemy(1);
               enemies.add(rightEnemy);
            }
           // if all the enemies have been killed in the level,
           // then go to the next level
            if (level.allEnemiesKilledInLevel()) {
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
      for (Enemy s : enemies) {
         if (s.getHealth() > 0) {
            game.batch.draw(Renderer.getInstance().getCurrentTexture(s), s.getLocation().x, s.getLocation().y);
         }
      }
   }

   public void renderCrates() {
      for (int i = 0; i < crates.size(); i++) {
         game.batch.draw(Renderer.getInstance().getCrateTexture(), crates.get(i).getLocation().x, crates.get(i).getLocation().y);
      }
   }

   public boolean isCollision(Rectangle objectA, Rectangle objectB) {
      return (objectA.x <= (objectB.x + objectB.width)) && (objectB.x <= (objectA.x + objectA.width));
   }

   public boolean isAttackCollision(Waldo waldo, Entity entity) {
      float distance = (waldo.getX() + waldo.getWidth()/2) - (entity.getX() + entity.getWidth()/2);
 
      LOGGER.log(Level.INFO, "Distance away: " + distance);
     
      Entity.Direction requiredDirection;

      if (distance >= 0) {
         requiredDirection = Entity.Direction.LEFT;
      } else {
         requiredDirection = Entity.Direction.RIGHT;
      }

      return (waldo.getDirection() == requiredDirection) && Math.abs(distance) < 150;
   }

   @Override
   public void dispose() {
      floorTexture.dispose();
      backgroundTexture.dispose();
      font.dispose();
      bgSound.dispose();
   }
}
