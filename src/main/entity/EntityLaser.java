package main.entity;

import javafx.scene.image.Image;
import main.Game;
import main.util.ImageStore;
import main.util.Reference;

public class EntityLaser extends Entity {
    private Image[] images;
    private int imageIndex = 0;
    private int imageCooldown = 25;
    private boolean isAlien;

    public EntityLaser(int health, double x, double y, double xVelocity, double yVelocity, boolean isAlien) {
        super(health, x, y, xVelocity, yVelocity);
        this.isAlien = isAlien;
        if (isAlien) {
            imageCooldown = 0;
            imageIndex = 4;
        }
    }

    @Override
    public Image getImage() {
        if (images != null) {
            return images[imageIndex];
        } else {
            images = new Image[Reference.LASER_IMAGE_COUNT];
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageStore.getImage("laser-" + i);
            }
            return images[imageIndex];
        }
    }

    @Override
    public void update() {
        super.update();

        if (y <= Reference.BACKGROUND_WALL_HEIGHT || y >= Reference.GAME_HEIGHT - Reference.LASER_HEIGHT - Reference.BACKGROUND_WALL_HEIGHT) {
            health = 0;
        }

        if (!isAlien) {
            imageCooldown = imageCooldown > 1 ? imageCooldown - 1 : 0;
            if (imageCooldown <= 0) {
                imageIndex = (imageIndex + 1) % 4;
                imageCooldown = 25;
            }
        }
    }

    @Override
    public int getDamageDealt() {
        return Reference.DAMAGE_DEALT_LASER;
    }

    public boolean getIsAlien() {
        return isAlien;
    }
}
