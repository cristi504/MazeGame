package com.example.mazegame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.layout.Pane;

import java.util.*;

import static javafx.scene.paint.Color.DARKGRAY;

public class MazeGame extends Application {

    private int score = 0;
    private static Stage mazeStage;
    private static int timeLeft = 20;  // Initial time (20 seconds)
    private static Text timerText; // Display timer
    private static Timeline timer;
    // Scorul initial

    @Override
    public void start(Stage primaryStage) {
        // Crearea ferestrei de start
        StackPane startRoot = new StackPane();
        startRoot.setStyle("-fx-background-color: black;"); // Setăm fundalul la negru
        Text startText = new Text("Welcome to the Maze Game! \n        Press Start to Play");
        startText.setFont(Font.font("Arial", 50));
        startText.setFill(Color.WHITE);
        startText.textAlignmentProperty();

        Button startButton = new Button("Start Maze");
        startButton.setFont(Font.font("Arial",30));
        startButton.setTextFill(Color.WHITE);
        startButton.setStyle("-fx-background-color: gray;");
        startButton.setOnAction(e -> {
            // Când apesi pe butonul "Start", fereastra de start se închide și se deschide fereastra cu labirintul
            primaryStage.close();  // Închide fereastra de start
            openMazeWindow();      // Deschide fereastra cu labirintul
        });

        // Creează un StackPane și adaugă atât textul, cât și butonul

        startRoot.getChildren().addAll(startText, startButton);  // Adaugă textul și butonul în StackPane

        // Poziționează butonul mai jos, dacă dorești
        StackPane.setAlignment(startButton, Pos.BOTTOM_CENTER);  // Aliniaza butonul la fundul ferestrei
        StackPane.setAlignment(startText, Pos.CENTER);  // Aliniaza textul pe centru

        // Setăm scena ferestrei de start
        Scene startScene = new Scene(startRoot, 1000, 600);
        primaryStage.setTitle("Start Window");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    private void openMazeWindow() {
        // Creăm o fereastră nouă pentru labirint
        System.out.println("Opening maze window...");
        mazeStage = new Stage();
        Pane root = new Pane();
        MazeLayout.MAZE_LAYOUT_LEVEL_1 = MazeGenerator.generateMaze();
        System.out.println("Maze generated!");
        MazeLayout.drawMaze(root);
        System.out.println("Maze draw function called!");
        // Folosim un Pane pentru a plasa elementele

        // Crearea și stilizarea textului pentru scor
        Text scoreText = new Text(10, 20, "Score: 0");
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setFill(Color.WHITE);
        root.getChildren().add(scoreText);
        // Timer text
        timerText = new Text(10, 50, "Time: " + timeLeft);
        timerText.setFont(Font.font("Arial", 20));
        timerText.setFill(Color.YELLOW);
        timerText.setTranslateX(100);
        timerText.setTranslateY(-30);
        root.getChildren().add(timerText);

        // Crearea colectabilelor
        List<Collectible> collectibles = new ArrayList<>();
        Random rand = new Random();
        Set<String> usedPositions = new HashSet<>();

        // Prevent collectibles from spawning on top of the player or door
        int playerStartRow = 1;
        int playerStartCol = 1;
        usedPositions.add(playerStartRow + "," + playerStartCol);

        int doorRow = MazeLayout.ROWS - 2;
        int doorCol = MazeLayout.COLUMNS - 2;
        usedPositions.add(doorRow + "," + doorCol);

        // Optionally avoid placing collectible near the door path cells too:
        usedPositions.add((doorRow - 1) + "," + doorCol);
        usedPositions.add(doorRow + "," + (doorCol - 1));

        // Generate collectibles
        for (int i = 0; i < 20; i++) {
            int row, col;
            do {
                row = rand.nextInt(MazeLayout.ROWS);
                col = rand.nextInt(MazeLayout.COLUMNS);
            } while (
                    MazeLayout.MAZE_LAYOUT_LEVEL_1[row][col] == 1 ||
                            usedPositions.contains(row + "," + col)
            );

            usedPositions.add(row + "," + col);

            String[] images = {
                    "D:/java/MazeGame/photos/gem.png",
                    "D:/java/MazeGame/photos/pink_gem.png",
                    "D:/java/MazeGame/photos/coin.png"
            };
            Map<String, Integer> collectibleValues = new HashMap<>();
            collectibleValues.put("D:/java/MazeGame/photos/coin.png", 1);
            collectibleValues.put("D:/java/MazeGame/photos/gem.png", 3);
            collectibleValues.put("D:/java/MazeGame/photos/pink_gem.png", 5);
            String randomImage = images[rand.nextInt(images.length)];
            int value = collectibleValues.getOrDefault(randomImage, 1);
            collectibles.add(new Collectible(root, row, col, randomImage, value));
        }


        // Creăm scena cu labirintul
        Scene mazeScene = new Scene(root, 1230, 830);
        mazeStage.setTitle("Maze Game");
        mazeStage.setScene(mazeScene);
        mazeStage.show();

        // Crearea jucătorului
        Player player = new Player(root, collectibles, scoreText);
        mazeScene.setOnKeyPressed(e -> player.movePlayer(e.getCode()));  // Mișcarea jucătorului cu tastele
        startTimer();
    }
    private static void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timerText.setText("Time: " + timeLeft);
            if (timeLeft <= 0) {
                timer.stop();
                showGameOverWindow(0); // Time ran out, score = 0
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public static void increaseTime(int seconds) {
        timeLeft += seconds;
        timerText.setText("Time: " + timeLeft);
    }

    // Metoda pentru a deschide fereastra Game Over
    public static void showGameOverWindow(int score) {
        // Creează o nouă fereastră pentru "Game Over"
        if (timer != null) timer.stop();
        Stage gameOverStage = new Stage();

        // Creează un StackPane pentru a organiza elementele UI
        StackPane stackPane = new StackPane();

        // Creează un mesaj pentru "Game Over"
        Label gameOverMessage = new Label("Game Over! ");
        gameOverMessage.setStyle("-fx-font-size: 20px; -fx-text-fill: red;");

        // Creează un mesaj pentru scor
        Label scoreMessage = new Label("Your Score: " + score);
        scoreMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        // Creează și adaugă un buton pentru a închide fereastra
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            gameOverStage.close();  // Close Game Over window
            MazeGame.closeMazeWindow(); // Close the Maze window (we add this function below)
        });


        // Adaugă elementele în StackPane
        VBox vbox = new VBox(10, gameOverMessage, scoreMessage, closeButton);
        vbox.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(vbox);

        // Creează și configurează scena
        Scene gameOverScene = new Scene(stackPane, 300, 200);
        stackPane.setStyle("-fx-background-color: black;"); // Fundal negru pentru stilizare
        gameOverStage.setScene(gameOverScene);
        gameOverStage.setTitle("Game Over");
        gameOverStage.show(); // Arată fereastra
    }
    public static void closeMazeWindow() {  //  Close Maze Window
        if (mazeStage != null) {
            mazeStage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
