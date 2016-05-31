package com.lifemm.game;

import com.badlogic.gdx.math.Rectangle;

public class Collision {
   
   public boolean isCollision(Rectangle objectA, Rectangle objectB) {
      return (objectA.x <= (objectB.x + objectB.width)) && (objectB.x <= (objectA.x + objectA.width));
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
}
