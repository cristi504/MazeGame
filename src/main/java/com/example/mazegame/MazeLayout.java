package com.example.mazegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class MazeLayout {
    public static final int TILE_SIZE = 40;
    public static final int COLUMNS = 30;
    public static final int ROWS = 20;
    public static final int[][] MAZE_LAYOUT_LEVEL_1 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static void drawMaze(Pane root) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++){
                if (MAZE_LAYOUT_LEVEL_1[row][col] == 1) {
                    Rectangle wall = new Rectangle(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    wall.setFill(Color.BLACK);
                    root.getChildren().add(wall);
                }
                else if (MAZE_LAYOUT_LEVEL_1[row][col] == 2) { // Exit
                    Image exitImage = new Image("D:/java/MazeGame/photos/door.png"); // Exit color
                    ImageView exitImageView = new ImageView(exitImage);
                    exitImageView.setX(col * TILE_SIZE);
                    exitImageView.setY(row * TILE_SIZE);
                    exitImageView.setFitWidth(TILE_SIZE);  // Ajustează dimensiunile imaginii
                    exitImageView.setFitHeight(TILE_SIZE);
                    root.getChildren().add(exitImageView);
                }
            }
        }
    }

    // Metoda pentru a returna labirintul

}

//public class MazeLayout {
//    public static final int TILE_SIZE = 40;
//    public static final int COLUMNS = 30;
//    public static final int ROWS = 20;
//    public static int[][] MAZE_LAYOUT_LEVEL_1 = MazeGenerator.generateMaze();
//
//    public static void drawMaze(Pane root) {
//        System.out.println("Drawing Maze...");
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLUMNS; col++) {
//                if (MAZE_LAYOUT_LEVEL_1[row][col] == 1) {
//                    Rectangle wall = new Rectangle(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//                    wall.setFill(Color.BLACK);
//                    root.getChildren().add(wall);
//                } else if (MAZE_LAYOUT_LEVEL_1[row][col] == 0) {
//                    // Path is empty, do nothing
//                } else if (MAZE_LAYOUT_LEVEL_1[row][col] == 2) {
//                    System.out.println("Placing exit at: " + row + "," + col);
//                    // Exit placement
//                    Image exitImage = new Image("D:/java/MazeGame/photos/door.png");
//                    ImageView exitImageView = new ImageView(exitImage);
//                    exitImageView.setX(col * TILE_SIZE);
//                    exitImageView.setY(row * TILE_SIZE);
//                    exitImageView.setFitWidth(TILE_SIZE);
//                    exitImageView.setFitHeight(TILE_SIZE);
//                    root.getChildren().add(exitImageView);
//                }
//            }
//        }
//        System.out.println("Maze drawn successfully!");
//        System.out.println(Arrays.deepToString(MAZE_LAYOUT_LEVEL_1).replace("],", "],\n"));
//
//    }
//
//       public int[][] getMaze() {
//        return MAZE_LAYOUT_LEVEL_1;
//     }
//}
//
//
