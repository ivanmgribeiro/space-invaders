package main.util;

import javafx.scene.image.Image;
import main.Game;

import java.util.HashMap;
import java.util.Map;

public class ImageStore {
    private static Map<String, Image> IMAGE_MAP = new HashMap<>();

    public static void loadImages() {
        IMAGE_MAP.put("alien", new Image(Game.class.getResourceAsStream("/res/alien.png"),
                Reference.ALIEN_WIDTH,
                Reference.ALIEN_HEIGHT,
                true,
                true));
        IMAGE_MAP.put("laser-0", new Image(Game.class.getResourceAsStream("/res/laser-0.png")));
        IMAGE_MAP.put("laser-1", new Image(Game.class.getResourceAsStream("/res/laser-1.png")));
        IMAGE_MAP.put("laser-2", new Image(Game.class.getResourceAsStream("/res/laser-2.png")));
        IMAGE_MAP.put("laser-3", new Image(Game.class.getResourceAsStream("/res/laser-3.png")));
        IMAGE_MAP.put("laser-4", new Image(Game.class.getResourceAsStream("/res/laser-4.png")));
        IMAGE_MAP.put("background", new Image(Game.class.getResourceAsStream("/res/background.png"),
                Reference.BACKGROUND_WIDTH,
                Reference.BACKGROUND_HEIGHT,
                true,
                true));
        IMAGE_MAP.put("player", new Image(Game.class.getResourceAsStream("/res/player.png"), 100, 150, true, true));
    }

    /*
        Get image from internal name
     */
    public static Image getImage(String name) {
        return IMAGE_MAP.get(name);
    }
}
