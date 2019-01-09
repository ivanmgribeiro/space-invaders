package main.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.util.ImageStore;
import main.util.Reference;

public class EntityPlayer extends Entity {
    static Image image;

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
        boolean left = false;
        for (KeyCode c : Reference.KEYS_LEFT) {
            left |= Reference.keyMap.getOrDefault(c, false);
        }

        boolean right = false;
        for (KeyCode c : Reference.KEYS_RIGHT) {
            right |= Reference.keyMap.getOrDefault(c, false);
        }

        if (right && x <= Reference.GAME_WIDTH - Reference.BACKGROUND_WALL_WIDTH - getImage().getWidth()) {
            xVelocity = Reference.PLAYER_MOVESPEED;
        } else if (left && x > Reference.BACKGROUND_WALL_WIDTH) {
            xVelocity = -Reference.PLAYER_MOVESPEED;
        } else {
            xVelocity = 0;
        }

        x += xVelocity;
    }
}
