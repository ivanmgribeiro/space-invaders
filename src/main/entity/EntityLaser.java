package main.entity;

import javafx.scene.image.Image;
import main.Game;
import main.util.ImageStore;

public class EntityLaser extends Entity {
    Image[] images;
    int imageCounter = 0;

    public EntityLaser(int health, double x, double y, double xVelocity, double yVelocity) {
        super(health, x, y, xVelocity, yVelocity);
    }

    @Override
    public Image getImage() {
        if (images != null) {
            return images[imageCounter];
        } else {
            images = new Image[4];
            for (int i = 0; i < 4; i++) {
                images[i] = ImageStore.getImage("laser-" + i);
            }
            return images[imageCounter];
        }
    }

    @Override
    public void update() {
        imageCounter = (imageCounter+1) % 4;
        super.update();

        if (y <= 0) {
            y = 600;
        }
    }
}
