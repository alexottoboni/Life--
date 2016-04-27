package com.lifemm.game;

/*
This represents a basic on screen object.
*/

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

abstract public class Entity {
    private static final int FLOOR = 235;
    public enum Direction {UP, DOWN, LEFT, RIGHT};
    protected Rectangle location;
    private float x_velocity;
    private float y_velocity;
    private float x_acceleration;
    private float y_acceleration;
    private float health;
    protected Direction direction;

    public Entity() {
        location = new Rectangle();
    }

    public Rectangle getLocation() {
        return location;
    }

    public void update() {
        location.x += x_velocity;
        if (location.y + y_velocity < FLOOR) {
           location.y = FLOOR;
        } else {
           location.y += y_velocity;
        }
        x_velocity += x_acceleration;
        y_velocity += y_acceleration;
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
        x_velocity = xvel;
    }

    public float getXVelocity() {
        return x_velocity;
    }

    public void setYVelocity(float yvel) {
        y_velocity = yvel;
    }
    public float getYVelocity() {
        return y_velocity;
    }

    public void setXAcceleration(float xacc) {
        x_acceleration = xacc;
    }
    public float getXAcceleration() {
        return x_acceleration;
    }

    public void setYAcceleration(float yacc) {
        y_acceleration = yacc;
    }
    public float getYAcceleration() {
        return y_acceleration;
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

    public abstract Texture getCurrentTexture();
}
