
package com.example.mazegame;

import java.util.*;

public class MazeGenerator {
    public static int[][] generateMaze() {
        int rows = MazeLayout.ROWS;
        int cols = MazeLayout.COLUMNS;
        int[][] maze = new int[rows][cols];

        // Fill the grid with walls
        for (int i = 0; i < rows; i++) {
            Arrays.fill(maze[i], 1);
        }

        //for door
        int exitRow = MazeLayout.ROWS - 2;
        int exitCol = MazeLayout.COLUMNS - 2;
        maze[exitRow][exitCol] = 2;

        maze[exitRow][exitCol - 1] = 0;
        maze[exitRow - 1][exitCol] = 0;

        // Recursive Backtracking Algorithm to generate the maze
        Stack<int[]> stack = new Stack<>();
        int startRow = 1, startCol = 1;
        maze[startRow][startCol] = 0; // Start with an open cell
        stack.push(new int[]{startRow, startCol});

        int[][] directions = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}}; // Move in 2-cell steps
        Random rand = new Random();

        while (!stack.isEmpty()) {
            int[] cell = stack.peek();
            int row = cell[0];
            int col = cell[1];

            // Collects eligible neighbors at 1 space/wall distance (not immediate neighbours)
            List<int[]> neighbors = new ArrayList<>();

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (newRow > 0 && newRow <= rows - 1 && newCol > 0 && newCol <= cols -1  && maze[newRow][newCol] == 1) {
                    neighbors.add(new int[]{newRow, newCol});
                }
            }

            if (!neighbors.isEmpty()) {
                int[] chosen = neighbors.get(rand.nextInt(neighbors.size()));
                int newRow = chosen[0];
                int newCol = chosen[1];

                // Remove wall between cells, because "neighbours" are at one space distance (not immediate)
                maze[(row + newRow) / 2][(col + newCol) / 2] = 0;
                maze[newRow][newCol] = 0;

                stack.push(chosen);
            } else {
                stack.pop();
            }
        }
        return maze;
    }
}
