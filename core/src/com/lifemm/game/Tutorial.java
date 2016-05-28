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
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public class Tutorial extends ScreenOverride {
   final LifeMM game;
   
   // States
   public enum TutorialState {INTRO, MOVEMENT, PLACEBLOCK, ATTACK, FINISHED};

   // Main Character and Treasure
   Waldo waldo;
   Treasure treasure;

   // Fonts
   BitmapFont font;

   // List of entities that there can be multiple of
   public ArrayList<Spider> enemies;
   public ArrayList<Crate> crates;

   // Textures for the background and floor
   public static Texture backgroundTexture;
   public static Texture floorTexture;

   // Constants
   private static final int FLOOR = 235;
   private static final int ATTACKING = 1;
   private static final int MOVING = 2;
   private static final int JUMPING = 3;
   private static final int BUILDING = 4;
   private static final int LEFT = -1;
   private static final int RIGHT = 1;

   // Globals
   public TutorialState state = TutorialState.INTRO;
   public Stopwatch time;
   public boolean hasJumped;
   public boolean hasMovedLeft;
   public boolean hasMovedRight;
   public boolean hasPlacedBlock;
   public boolean hasKilledEnemy;
   private Renderer renderer;

   public Tutorial (final LifeMM game) {
      this.game = game;
      
      // Initialize timer
      time = new Stopwatch();

      // Initialize Hero Characters
      waldo = new Waldo();
      treasure = new Treasure();

      // Sections of tutorial
      hasJumped = false;
      hasMovedLeft = false;
      hasMovedRight = false;
      hasPlacedBlock = false;
      hasKilledEnemy = false;

      // Initialize lists
      crates = new ArrayList<>();
      enemies = new ArrayList<>();

      // Generate the font we use
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 46;
      parameter.borderColor = Color.BLACK;
      parameter.borderWidth = 2;
      font = generator.generateFont(parameter);

      // Load Textures
      backgroundTexture = new Texture("bg4.png");
      floorTexture = new Texture("floor.png");
   
      // Intialize Renderer
      renderer = Renderer.getInstance();
   }   

   @Override
   public void render (float delta) {
      if (this.getTutorialState() == TutorialState.INTRO) {
         doIntro();
      } else if (this.getTutorialState() == TutorialState.MOVEMENT) {
         doMovement(); 
      } else if (this.getTutorialState() == TutorialState.PLACEBLOCK) {
         doPlaceBlock();
      } else if (this.getTutorialState() == TutorialState.ATTACK) {
         enemies.add(new Spider());
         doAttack();
      } else if (this.getTutorialState() == TutorialState.FINISHED) {
         doFinished();
      }
      time.lap();
   }

   public void doIntro() {
      // Clears the screen, don't remove
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      game.batch.begin();
      if ((int)time.getTime() < 3) {
         font.draw(game.batch, "Welcome to Life--", 1440/2 - 200, 810/2);
      } else if ((int)time.getTime() >= 3 && (int)time.getTime() < 6) {
         font.draw(game.batch, "You control the hero Waldo", 1440/2 - 300, 600);
         game.batch.draw(renderer.getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);
      } else if ((int)time.getTime() >= 6 && (int)time.getTime() < 12) {
         font.draw(game.batch, "Protect the treasure!", 1440/2 - 200, 600);
         game.batch.draw(Renderer.getInstance().getTreasureTexture(), treasure.getLocation().x, treasure.getLocation().y);
      } else {
         this.setTutorialState(TutorialState.MOVEMENT);
      }
      game.batch.end();
   }

   public void doMovement() {
      // Clears the screen, don't remove
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // Checks for player input and movement
      updateWaldoMovement();
      waldo.updateStateTime();
      waldo.updateState();
      waldo.update();
      boundsCheckWaldo();

      game.batch.begin();
      renderBG();
      game.batch.draw(Renderer.getInstance().getTreasureTexture(), treasure.getLocation().x, treasure.getLocation().y);
      game.batch.draw(renderer.getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);
      font.draw(game.batch, "Press the arrow keys to move", 1440/2 - 400, 600);
      game.batch.end();

      if (hasMovedRight && hasMovedLeft && hasJumped) {
         this.setTutorialState(TutorialState.PLACEBLOCK);
      }
   }

   public void doPlaceBlock() {
      // Clears the screen, don't remove
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // Checks for player input and movement
      updateWaldoMovement();
      waldo.updateStateTime();
      waldo.updateState();
      waldo.update();
      boundsCheckWaldo();

      game.batch.begin();
      renderBG();
      game.batch.draw(Renderer.getInstance().getTreasureTexture(), treasure.getLocation().x, treasure.getLocation().y);
      game.batch.draw(renderer.getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);
      font.draw(game.batch, "Press 'A' to place a block", 1440/2 - 400, 600);
      renderCrates();
      game.batch.end();
      if (hasPlacedBlock) {
         this.setTutorialState(TutorialState.ATTACK);
      }
   }

   public void doAttack() {
      // Clears the screen, don't remove
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // Checks for player input and movement
      updateWaldoMovement();
      waldo.updateStateTime();
      waldo.updateState();
      waldo.update();
      boundsCheckWaldo();

      game.batch.begin();
      renderBG();
      renderEnemies();
      deleteDeadEnemies();
      game.batch.draw(Renderer.getInstance().getTreasureTexture(), treasure.getLocation().x, treasure.getLocation().y);
      game.batch.draw(renderer.getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);
      font.draw(game.batch, "Press 'SPACE' to attack", 1440/2 - 400, 600);
      renderCrates();
      game.batch.end();
      if (hasKilledEnemy) {
         this.setTutorialState(TutorialState.FINISHED);
      }
   }

   public void doFinished() {
      // Clears the screen, don't remove
      Gdx.gl.glClearColor(1, 1, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // Checks for player input and movement
      updateWaldoMovement();
      waldo.updateStateTime();
      waldo.updateState();
      waldo.update();
      boundsCheckWaldo();

      game.batch.begin();
      renderBG();
      game.batch.draw(Renderer.getInstance().getTreasureTexture(), treasure.getLocation().x, treasure.getLocation().y);
      game.batch.draw(renderer.getCurrentTexture(waldo), waldo.getLocation().x, waldo.getLocation().y);
      font.draw(game.batch, "Press 'ESC' to leave tutorial", 1440/2 - 400, 600);
      game.batch.end();
      if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
         game.setScreen(new MainMenu(game));
         dispose();
      }
   }

   public TutorialState getTutorialState() {
      return this.state;
   }

   public void setTutorialState(TutorialState state) {
      this.state = state;
   }

   public void renderBG() {
      game.batch.draw(backgroundTexture, 0, 0);
      game.batch.draw(floorTexture, 0, 0);
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

   public void renderCrates() {
         for (int i = 0; i < crates.size(); i++) {
            game.batch.draw(crates.get(i).getCurrentTexture(), crates.get(i).getLocation().x, crates.get(i).getLocation().y);
         }
   }

   // Check what enemies are alive
   public void deleteDeadEnemies() {
      for (int i = 0; i < enemies.size(); i++) {
         if (enemies.get(i).getHealth() <= 0) {
            enemies.remove(enemies.get(i));
            hasKilledEnemy = true;
         }
      }
   }

   // Update the state and position of the main character based on user input
   public void updateWaldoMovement() {
    if (Gdx.input.isKeyPressed(Keys.UP) && waldo.getLocation().y == FLOOR) {
         if (waldo.getLocation().y == FLOOR) {
            waldo.setYVelocity(15);
            waldo.setYAcceleration(-1);
         }
         hasJumped = true;
      }

      if (Gdx.input.isKeyPressed(Keys.SPACE) && waldo.getState() != ATTACKING) {
         waldo.setState(ATTACKING);
         for (Entity s : enemies) {
            if (isAttackCollision(waldo, s)) {
               s.setHealth(s.getHealth() - 300);
            }
         }
      }

      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
         waldo.setXVelocity(-4);
         waldo.setDirection(Entity.Direction.LEFT);
         hasMovedLeft = true;
      }
      
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
         waldo.setXVelocity(4);
         waldo.setDirection(Entity.Direction.RIGHT);
         hasMovedRight = true;
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

         waldo.setState(BUILDING);
         hasPlacedBlock = true;
         if (waldo.getDirection() == Entity.Direction.LEFT) {
            crates.add(new Crate(waldo.getLocation().x - 128));
         } else {
            crates.add(new Crate(waldo.getLocation().x + 128));
         }
      }
   }

   public boolean isAttackCollision(Waldo waldo, Entity entity) {
      float distance = (waldo.getX() + waldo.getWidth()/2) - (entity.getX() + entity.getWidth()/2);
 
      Entity.Direction requiredDirection;

      if (distance >= 0) {
         requiredDirection = Entity.Direction.LEFT;
      } else {
         requiredDirection = Entity.Direction.RIGHT;
      }

      return (waldo.getDirection() == requiredDirection) && Math.abs(distance) < 150;
   }

   public void renderEnemies() {
      for (Spider s : enemies) {
         if (s.getHealth() > 0) {
            game.batch.draw(s.getCurrentTexture(), s.getLocation().x, s.getLocation().y);
         }
      }
   }
}
