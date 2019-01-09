package main.entity;

import javafx.scene.image.Image;

public abstract class Entity {
    private int health;
    protected double x, y, xVelocity, yVelocity;

    public Entity(int health, double x, double y, double xVelocity, double yVelocity) {
        this.health = health;
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public int getHealth() {
        return health;
    };

    public double getX() {
        return x;
    };

    public double getY() {
        return y;
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;
    }

    public void doDamage(int dmg) {
        health -= dmg;
        if (health <= 0) {
            // TODO: kill this entity
        }
    }

    public abstract Image getImage();

}
