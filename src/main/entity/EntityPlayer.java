package main.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.util.ImageStore;
import main.util.Reference;

public class EntityPlayer extends Entity {
    static Image image;
    private int cooldown = 0;
    private boolean canFire = true;

    public EntityPlayer(int health, double x, double y, double xVelocity, double yVelocity) {
        super(health, x, y, xVelocity, yVelocity);
    }

    @Override
    public Image getImage() {
        if (image != null) {
            return image;
        } else {
            image = ImageStore.getImage("player");
            return image;
        }
    }

    @Override
    public void update() {
        // update location & movement
        // TODO: move the velocity updates to another method so it can be done only when needed
        boolean left = false;
        for (KeyCode c : Reference.KEYS_LEFT) {
            left |= Reference.keyMap.getOrDefault(c, false);
        }

        boolean right = false;
        for (KeyCode c : Reference.KEYS_RIGHT) {
            right |= Reference.keyMap.getOrDefault(c, false);
        }

        if (right && x <= Reference.GAME_WIDTH - Reference.BACKGROUND_WALL_WIDTH - getImage().getWidth() && !left) {
            xVelocity = Reference.PLAYER_MOVESPEED;
        } else if (left && x > Reference.BACKGROUND_WALL_WIDTH && !right) {
            xVelocity = -Reference.PLAYER_MOVESPEED;
        } else {
            xVelocity = 0;
        }

        super.update();

        // firing
        // TODO also move this somewhere else
        // TODO when moving also move for Alien
        cooldown = cooldown > 1 ? cooldown-1 : 0;
        if (cooldown <= 0) {
            boolean fire = false;
            for (KeyCode c : Reference.KEYS_FIRE) {
                fire |= Reference.keyMap.getOrDefault(c, false);
            }

            canFire = fire;
        }
    }

    public boolean canFire() {
        return canFire;
    }

    public void signalFired() {
        canFire = false;
        cooldown = 50;
    }
}
