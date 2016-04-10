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

import java.util.ArrayList;

public class LifeMM extends ApplicationAdapter {
   
   // OpenGL Draw Object
	SpriteBatch batch;

   // Main Character and Treasure
   Waldo waldo;
   Treasure treasure;

   // Fonts
   BitmapFont font;
  
   // List of entities that currently exist
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
	
   // Function that gets called once to prepare everything needed for render
	@Override
	public void create () {
      // OpenGL Object used to draw everything on the screen
		batch = new SpriteBatch();

      // One of a kind entities
      waldo = new Waldo();
      treasure = new Treasure();

      // Load Textures
      backgroundTexture = new Texture("bg4.png");
      floorTexture = new Texture("floor.png");

      // Generate the font we use
      FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("GROBOLD.ttf"));
      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
      parameter.size = 46;
      parameter.borderColor = Color.BLACK;
      parameter.borderWidth = 2;
      font = generator.generateFont(parameter);

      // Create lists of entities 
      crates = new ArrayList<Crate>();
	}

   public void renderBG() {
      batch.draw(backgroundTexture, 0, 0);
      batch.draw(floorTexture, 0, 0);
   }

   void printDebugInfo() {
      System.out.println("DEBUG INFO");
      System.out.println("--------------------------------------");
      System.out.println("Waldo State: " + waldo.getState());
      System.out.println("Waldo X: " + (waldo.getLocation().x + waldo.getLocation().width/2));
      System.out.println("Waldo Y: " + waldo.getLocation().y);
      System.out.println("Waldo Health: " + waldo.getHealth());
      System.out.println("Waldo Direction: " + waldo.getDirection());
      System.out.println("Treasure X: " + (treasure.getLocation().x + treasure.getLocation().width/2));
      System.out.println("Treasure Health: " + treasure.getHealth());
      System.out.println("--------------------------------------");
      System.out.println("");
   }

	@Override
	public void render () {
      // Clears the screen, don't remove
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      // Keep track of the number of frames that the
      // main character has spent in his current state
      waldo.updateStateTime();

      // Update what the main character is doing,
      // for example update his Y position based on how long
      // he has been in the jumping state for.
      waldo.updateState();

      // Accept user input
      updateWaldoMovement();
      boundsCheckWaldo();

      if (Gdx.input.isKeyPressed(Keys.DEL)) {
         printDebugInfo();
      }

		batch.begin();
      renderBG();
      batch.draw(treasure.getCurrentTexture(), treasure.getLocation().x, treasure.getLocation().y);
		batch.draw(waldo.getCurrentTexture(), waldo.getLocation().x, waldo.getLocation().y);
      font.draw(batch, "Score " + waldo.getScore(), 1000, 700);
      renderCrates();
		batch.end();

   }

   // Update the state and position of the main character based on user input
   public void updateWaldoMovement() {
    if (Gdx.input.isKeyPressed(Keys.UP)) {
         // Set state to jumping
         if (waldo.getLocation().y == FLOOR && waldo.getState() == MOVING) {
            waldo.setState(JUMPING);
         }
      }

      if (Gdx.input.isKeyPressed(Keys.SPACE)) {
         System.out.println(isAttackCollision(waldo.getLocation(), treasure.getLocation()));
      }

      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
         waldo.setLocationX(waldo.getLocation().x - 300 * Gdx.graphics.getDeltaTime());
         waldo.setDirection(LEFT);
      }
      
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
         waldo.setLocationX(waldo.getLocation().x + 300 * Gdx.graphics.getDeltaTime());
         waldo.setDirection(RIGHT);
      }

      if (Gdx.input.isKeyPressed(Keys.A)) {
         if (waldo.getState() != BUILDING) {
            Rectangle temp = new Rectangle(waldo.getLocation());
            if (waldo.getDirection() == LEFT) {
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
            if (waldo.getDirection() == LEFT) {
               crates.add(new Crate(waldo.getLocation().x - 128));
            } else {
               crates.add(new Crate(waldo.getLocation().x + 128));
            }
         }
      }
   }

   void boundsCheckWaldo() {
      if (waldo.getLocation().y < FLOOR) {
         waldo.setLocationY(FLOOR);
      }

      if (waldo.getLocation().x < 0) {
         waldo.setLocationX(0);
      }

      if (waldo.getLocation().x > 1440 - 128) {
         waldo.setLocationX(1440 - 128);
      }

      if (waldo.getState() == MOVING && waldo.getLocation().y != FLOOR) {
         waldo.setLocationY(FLOOR);
      }

   }

   public void renderCrates() {
      for (int i = 0; i < crates.size(); i++) {
         batch.draw(crates.get(i).getCurrentTexture(), crates.get(i).getLocation().x, crates.get(i).getLocation().y);
      }
   }

   public boolean isCollision(Rectangle objectA, Rectangle objectB) {
      return (objectA.x <= (objectB.x + objectB.width)) && (objectB.x <= (objectA.x + objectA.width));
   }

   public boolean isAttackCollision(Rectangle player, Rectangle object) {
      if (Math.abs((player.x + player.width/2) - (object.x + object.width/2)) < 150) {
         return true;
      } else {
         return false;
      }
   }
}
