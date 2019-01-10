package main.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Game;
import main.util.ImageStore;
import main.util.Reference;

import java.util.Random;

public class EntityAlien extends Entity {
    private static Random rand = new Random();
    private static Image image;
    private boolean canFire = false;
    private int cooldown;

    public EntityAlien(int health, double x, double y, double xVelocity, double yVelocity) {
        super(health, x, y, xVelocity, yVelocity);
        cooldown = (int)(2 + 2*rand.nextGaussian());
    }

    @Override
    public void update() {
        if ((x < Reference.BACKGROUND_WALL_WIDTH && xVelocity < 0)
           || (x > Reference.GAME_WIDTH - Reference.BACKGROUND_WALL_WIDTH - getImage().getWidth() && xVelocity > 0)) {
            xVelocity = -xVelocity;
        }

        super.update();

        cooldown = cooldown > 1 ? cooldown-1 : 0;
        if (cooldown <= 0) {
            canFire = true;
        }
    }

    @Override
    public Image getImage() {
        if (image != null) {
            return image;
        } else {
            image = ImageStore.getImage("alien");
            return image;
        }
    }

    public boolean canFire() {
        return canFire;
    }

    public void signalFired() {
        canFire = false;
        cooldown = (int)(100 + 100*rand.nextGaussian());
    }
}
