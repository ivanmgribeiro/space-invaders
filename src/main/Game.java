package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.entity.Entity;
import main.entity.EntityAlien;
import main.entity.EntityPlayer;
import main.map.ScreenMap;
import main.util.ImageStore;
import main.util.Reference;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends Application {
    private static Stage stage;
    private static StackPane root;
    private static Canvas canvas;
    private static boolean isRunning = false;
    private static ScreenMap map;
    private static Random rand = new Random();
    private double renderTime = 0;
    private double renderCount = 0;
    private double updateTime = 0;
    private double updateCount = 0;

    /*
    private ScheduledExecutorService renderExecutor = Executors.newScheduledThreadPool(1);
    private Runnable renderRunnable = () -> {
        if (isRunning) {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // draw background

            // draw entities
            for (Entity entity : map.getEntityList()) {
                canvas.getGraphicsContext2D().drawImage(entity.getImage(), entity.getX(), entity.getY());
            }
        } else {
            System.out.println("shutting down renderer");
            renderExecutor.shutdownNow();
        }
    };*/

    private AnimationTimer renderTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (isRunning) {
                double start = System.nanoTime();
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // draw background
                int bgTilesAcross = (int) Math.ceil(Reference.GAME_WIDTH / Reference.BACKGROUND_WIDTH);
                int bgTilesDown = (int) Math.ceil(Reference.GAME_HEIGHT / Reference.BACKGROUND_HEIGHT);

                Image img = ImageStore.getImage("background");
                for (int i = 0; i < bgTilesAcross; i++) {
                    for (int j = 0; j < bgTilesDown; j++) {
                        canvas.getGraphicsContext2D().drawImage(img,
                                i*Reference.BACKGROUND_WIDTH,
                                j*Reference.BACKGROUND_HEIGHT);
                    }
                }

                // draw walls
                int wallTilesAcross = (int) Math.ceil(Reference.GAME_WIDTH / Reference.BACKGROUND_WALL_WIDTH);
                int wallTilesDown = (int) Math.ceil(Reference.GAME_HEIGHT / Reference.BACKGROUND_WALL_HEIGHT);

                canvas.getGraphicsContext2D().setFill(Color.FORESTGREEN);
                for (int i = 0; i < wallTilesAcross; i++) {
                    canvas.getGraphicsContext2D().fillRect(i*Reference.BACKGROUND_WALL_WIDTH,
                            0,
                            Reference.BACKGROUND_WALL_WIDTH,
                            Reference.BACKGROUND_WALL_HEIGHT);

                    canvas.getGraphicsContext2D().fillRect(i*Reference.BACKGROUND_WALL_WIDTH,
                            Reference.GAME_HEIGHT - Reference.BACKGROUND_WALL_HEIGHT,
                            Reference.BACKGROUND_WALL_WIDTH,
                            Reference.BACKGROUND_WALL_HEIGHT);
                }

                for (int i = 0; i < wallTilesDown; i++) {
                    canvas.getGraphicsContext2D().fillRect(0,
                            i*Reference.BACKGROUND_WALL_HEIGHT,
                            Reference.BACKGROUND_WALL_WIDTH,
                            Reference.BACKGROUND_WALL_HEIGHT);

                    canvas.getGraphicsContext2D().fillRect(Reference.GAME_WIDTH - Reference.BACKGROUND_WALL_WIDTH,
                            i*Reference.BACKGROUND_WALL_HEIGHT,
                            Reference.BACKGROUND_WALL_WIDTH,
                            Reference.BACKGROUND_WALL_HEIGHT);
                }

                // draw entities
                for (Entity entity : map.getEntityList()) {
                    canvas.getGraphicsContext2D().drawImage(entity.getImage(), entity.getX(), entity.getY());
                }

                double end = System.nanoTime();
                renderCount++;
                renderTime += end - start;
            } else {
                System.out.println("shutting down renderer");
                //renderExecutor.shutdownNow();
            }
        }
    };

    private ScheduledExecutorService updateExecutor = Executors.newScheduledThreadPool(1);
    private Runnable updateRunnable = () -> {
        double start = System.currentTimeMillis();
        if (isRunning) {
            for (Entity entity : map.getEntityList()) {
                entity.update();
            }
        } else {
            System.out.println("shutting down updater");
            updateExecutor.shutdownNow();
        }
        double end = System.currentTimeMillis();
        updateCount++;
        updateTime += end-start;
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        isRunning = true;
        stage = primaryStage;
        setupGame();
        setupScreen();

        renderTimer.start();
        updateExecutor.scheduleAtFixedRate(updateRunnable, 0, 10, TimeUnit.MILLISECONDS);
    }

    @Override
    public void stop() throws Exception {
        isRunning = false;
        System.out.printf("average render time: %f\n", renderTime/renderCount);
        System.out.printf("average update time: %f\n", updateTime/updateCount);
        super.stop();
    }

    private static void setupGame() {
        ImageStore.loadImages();

        map = new ScreenMap();
        map.addEntity(new EntityAlien(100, 240, 50, 3 + 4*rand.nextGaussian(), 0));
        map.addEntity(new EntityAlien(100, 240, 125, 3 + 4*rand.nextGaussian(), 0));
        map.addEntity(new EntityAlien(100, 240, 200, 3 + 4*rand.nextGaussian(), 0));
        map.addEntity(new EntityAlien(100, 240, 275, 3 + 4*rand.nextGaussian(), 0));
        map.addEntity(new EntityPlayer(100, 240, 600, 0, 0));


    }

    private static void setupScreen() {
        root = new StackPane();
        canvas = new Canvas(Reference.GAME_WIDTH, Reference.GAME_HEIGHT);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root, 480, 854));

        stage.getScene().setOnKeyPressed(e -> {
            Reference.keyMap.put(e.getCode(), true);
        });

        stage.getScene().setOnKeyReleased(e-> {
            Reference.keyMap.put(e.getCode(), false);
            printMap(Reference.keyMap);
        });

        stage.setHeight(720);
        stage.setWidth(480);
        stage.show();
    }

    // TODO remove
    private static void printMap(Map<KeyCode, Boolean> map) {
        for (KeyCode c : map.keySet()) {
            System.out.printf("%s: %s   |   ", c.toString(), map.get(c).toString());
        }
        System.out.println("\n");
    }
}
