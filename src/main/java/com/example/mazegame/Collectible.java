package com.example.mazegame;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;


public class Collectible {
   // private Shape item;// Collectible item (Circle or Rectangle)
    private ImageView item;
    private int row, col;    // Position of the collectible in the maze
    private Pane root;
    private int value;// The pane to add the collectible to

    public Collectible(Pane root, int row, int col, String imagePath, int value) {
        this.row = row;
        this.col = col;
        this.root = root;
        this.value=value;

        // Create a circle as the collectible (you can change this to a rectangle or other shapes)
        //item = new Circle(col * MazeLayout.TILE_SIZE + MazeLayout.TILE_SIZE / 2, row * MazeLayout.TILE_SIZE + MazeLayout.TILE_SIZE / 2, MazeLayout.TILE_SIZE / 4);
        //item.setFill(Color.RED);  // Set color (red for apple, or any color you want)
        Image image = new Image(imagePath);
        item = new ImageView(image);
        item.setFitWidth(MazeLayout.TILE_SIZE * 0.8);  // Ajustează dimensiunea
        item.setFitHeight(MazeLayout.TILE_SIZE * 0.8);
        item.setX(col * MazeLayout.TILE_SIZE + (MazeLayout.TILE_SIZE - item.getFitWidth()) / 2);
        item.setY(row * MazeLayout.TILE_SIZE + (MazeLayout.TILE_SIZE - item.getFitHeight()) / 2);

        root.getChildren().add(item);  // Add the item to the root pane
    }

    public Node getItem() {
        return item;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() { return value; }

    public void collect() {
        root.getChildren().remove(item);  // Remove the item when collected
    }
}

