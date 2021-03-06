package main.util;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Reference {
    public static final int TICK_TIME = 20;
    public static final double BACKGROUND_WALL_WIDTH = 40;
    public static final double BACKGROUND_WALL_HEIGHT = 40;
    public static final double BACKGROUND_WIDTH = 128;
    public static final double BACKGROUND_HEIGHT = 128;
    public static final double GAME_WIDTH = 480;
    public static final double GAME_HEIGHT = 720;
    public static final double ALIEN_WIDTH = 100;
    public static final double ALIEN_HEIGHT = 50;
    public static final double LASER_WIDTH = 6;
    public static final double LASER_HEIGHT = 24;
    public static final double LASER_SPEED = 4;
    public static final int LASER_IMAGE_COUNT = 5;
    public static final double PLAYER_MOVESPEED = 4;
    public static final int DAMAGE_DEALT_BASE = 1;
    public static final int DAMAGE_DEALT_LASER = 100;
    public static final int HEALTHBAR_HEIGHT = 5;
    public static Map<KeyCode, Boolean> keyMap = new ConcurrentHashMap<>();
    public static List<KeyCode> KEYS_LEFT = Arrays.asList(KeyCode.A, KeyCode.LEFT);
    public static List<KeyCode> KEYS_RIGHT = Arrays.asList(KeyCode.D, KeyCode.RIGHT);
    public static List<KeyCode> KEYS_FIRE = Arrays.asList(KeyCode.SPACE, KeyCode.UP);
}
