package main.entity;

import javafx.scene.image.Image;
import main.Game;
import main.util.ImageStore;
import main.util.Reference;

public class EntityAlien extends Entity {
    private static Image image;

    public EntityAlien(int health, double x, double y, double xVelocity, double yVelocity) {
        super(health, x, y, xVelocity, yVelocity);
    }

    @Override
    public void update() {
        if ((x < Reference.BACKGROUND_WALL_WIDTH && xVelocity < 0)
           || (x > Reference.GAME_WIDTH - Reference.BACKGROUND_WALL_WIDTH - getImage().getWidth() && xVelocity > 0)) {
            xVelocity = -xVelocity;
        }

        super.update();
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
}
