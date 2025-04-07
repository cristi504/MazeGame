package com.example.mazegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Iterator;
import java.util.List;

public class Player {
    private static final int TILE_SIZE = 40;
    private final List<Collectible> collectibles;
    //  private Circle player;
    private ImageView player;
    private int playerRow = 1, playerCol = 1;
    private int score = 0;
    private Text scoreText;
    private Pane root;


    //        public Player(Pane root) {
//            player = new Circle(playerCol * TILE_SIZE + TILE_SIZE / 2, playerRow * TILE_SIZE + TILE_SIZE / 2, TILE_SIZE / 3);
//            player.setFill(Color.BLUE);
//            root.getChildren().add(player);
//        }
    public Player(Pane root, List<Collectible> collectibles,Text scoreText) {
        this.root = root;
        this.collectibles = collectibles;
        this.scoreText = scoreText;
        Image snakeImage = new Image("D:/java/MazeGame/photos/snake.png"); // Load snake image
        player = new ImageView(snakeImage);
        player.setFitWidth(TILE_SIZE * 0.8); // Adjust width
        player.setFitHeight(TILE_SIZE * 0.8); // Adjust height
//         player.setX(playerCol * TILE_SIZE + TILE_SIZE * 0.1); // Center in tile
//         player.setY(playerRow * TILE_SIZE + TILE_SIZE * 0.1);
        player.setX(playerCol * TILE_SIZE + (TILE_SIZE - player.getFitWidth()) / 2);
        player.setY(playerRow * TILE_SIZE + (TILE_SIZE - player.getFitHeight()) / 2);

        root.getChildren().add(player);
    }

    public void movePlayer(KeyCode key) {
        int newRow = playerRow, newCol = playerCol;

        switch (key) {
            case UP:
                newRow--;
                break;
            case DOWN:
                newRow++;
                break;
            case LEFT:
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
        }

        if (MazeLayout.MAZE_LAYOUT_LEVEL_1[newRow][newCol] == 0) {
            playerRow = newRow;
            playerCol = newCol;
//                player.setX(playerCol * TILE_SIZE + TILE_SIZE / 2);
//                player.setY(playerRow * TILE_SIZE + TILE_SIZE / 2);
            player.setX(playerCol * TILE_SIZE + (TILE_SIZE - player.getFitWidth()) / 2);
            player.setY(playerRow * TILE_SIZE + (TILE_SIZE - player.getFitHeight()) / 2);
            for (Iterator<Collectible> it = collectibles.iterator(); it.hasNext(); ) {
                Collectible c = it.next();
                if (c.getRow() == playerRow && c.getCol() == playerCol) {
                    it.remove();  // Remove collectible
                    root.getChildren().remove(c);
                    score+=c.getValue();  // Increase score
                    c.collect();
                    scoreText.setText("Score: " + score);
                    MazeGame.increaseTime(5);//  update score text
                }
            }
        }
        // Verifică dacă jucătorul a ajuns la exit (valoarea 2 în matrice)
        if (MazeLayout.MAZE_LAYOUT_LEVEL_1[newRow][newCol] == 2) {
            MazeGame.showGameOverWindow(score);
        }
    }
    public ImageView getPlayer () {
        return player;
    }
    public int getScore () {
        return score;
    }

}