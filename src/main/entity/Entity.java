package main.entity;

import javafx.scene.image.Image;
import main.collision.BoundingBox;
import main.util.Reference;

public abstract class Entity {
    private BoundingBox boundingBox;
    private final float maxHealth;
    protected float health;
    protected double x, y, xVelocity, yVelocity;

    public Entity(float health, double x, double y, double xVelocity, double yVelocity) {
        this.maxHealth = health;
        this.health = health;
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.getImage();
        this.boundingBox = BoundingBox.generateBoundingBox(this);
    }

    public double getHealth() {
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;
        boundingBox.setX(x);
        boundingBox.setY(y);
    }

    public void doDamage(int dmg) {
        health -= dmg;
    }

    public abstract Image getImage();

    public int getDamageDealt() {
        return Reference.DAMAGE_DEALT_BASE;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}
