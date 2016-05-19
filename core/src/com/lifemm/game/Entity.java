package com.lifemm.game;

/*
This represents a basic on screen object.
*/

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    public static final int FLOOR = 235;
    public enum Direction {UP, DOWN, LEFT, RIGHT};
    protected Rectangle location;
    private float xVelocity;
    private float yVelocity;
    private float xAcceleration;
    private float yAcceleration;
    private float health;
    protected Direction direction;

    public Entity() {
        location = new Rectangle();
    }

    public Rectangle getLocation() {
        return location;
    }

    public void update() {
        location.x += xVelocity;
        if (location.y + yVelocity < FLOOR) {
           location.y = FLOOR;
        } else {
           location.y += yVelocity;
        }
        xVelocity += xAcceleration;
        yVelocity += yAcceleration;
    }

    public void setHeight(float height) {
       getLocation().height = height;
    }

    public float getHeight() {
       return getLocation().height;
    }

    public void setWidth(float width) {
       getLocation().width = width;
    }

    public float getWidth() {
       return getLocation().width;
    }

    public void setX(float x) {
        getLocation().x = x;
    }
    public float getX() {
        return getLocation().x;
    }

    public void setY(float y) {
        getLocation().y = y;
    }
    public float getY() {
        return getLocation().y;
    }

    public void setXVelocity(float xvel) {
        xVelocity = xvel;
    }

    public float getXVelocity() {
        return xVelocity;
    }

    public void setYVelocity(float yvel) {
        yVelocity = yvel;
    }
    public float getYVelocity() {
        return yVelocity;
    }

    public void setXAcceleration(float xacc) {
        xAcceleration = xacc;
    }
    public float getXAcceleration() {
        return xAcceleration;
    }

    public void setYAcceleration(float yacc) {
        yAcceleration = yacc;
    }
    public float getYAcceleration() {
        return yAcceleration;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setHealth(float health) {
       this.health = health;
    }

    public void addHealth(float health) {
        this.health += health;
    }

    public float getHealth() {
        return health;
    }

    public boolean collision(Entity e) {
        return (location.x <= (e.getLocation().x + e.getLocation().width)) && (e.getLocation().x <= (location.x + location.width));
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }
}
