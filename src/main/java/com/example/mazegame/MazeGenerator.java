//package com.example.mazegame;
//
//import java.util.*;
//
//public class MazeGenerator {
//    private static final int WALL = 1;
//    private static final int PATH = 0;
//    private static final int ROWS = 20;
//    private static final int COLUMNS = 30;
//    private static final int[][] maze = new int[ROWS][COLUMNS];
//
//    private static final Random rand = new Random();
//
//    public static int[][] generateMaze() {
//        // Fill the grid with walls
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLUMNS; col++) {
//                maze[row][col] = WALL;
//            }
//        }
//
//        // Start at a random position (must be odd for valid maze structure)
//        int startRow = rand.nextInt(ROWS / 2) * 2 + 1;
//        int startCol = rand.nextInt(COLUMNS / 2) * 2 + 1;
//
//        // Carve maze using randomized Prim's algorithm
//        carveMaze(startRow, startCol);
//
//        // Add an exit at a random location on the right edge
//        for (int row = 1; row < ROWS - 1; row++) {
//            if (maze[row][COLUMNS - 2] == PATH) {
//                maze[row][COLUMNS - 1] = PATH;
//                break;
//            }
//        }
//
//        return maze;
//    }
//
//    private static void carveMaze(int startRow, int startCol) {
//        List<int[]> walls = new ArrayList<>();
//        maze[startRow][startCol] = PATH;
//
//        // Add initial walls around the start position
//        addWalls(startRow, startCol, walls);
//
//        while (!walls.isEmpty()) {
//            int[] wall = walls.remove(rand.nextInt(walls.size()));
//            int row = wall[0], col = wall[1];
//
//            if (isWallValid(row, col)) {
//                maze[row][col] = PATH;
//                addWalls(row, col, walls);
//            }
//        }
//    }
//
//    private static void addWalls(int row, int col, List<int[]> walls) {
//        int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
//        for (int[] dir : directions) {
//            int newRow = row + dir[0];
//            int newCol = col + dir[1];
//            if (isInBounds(newRow, newCol) && maze[newRow][newCol] == WALL) {
//                walls.add(new int[]{newRow, newCol});
//                maze[row + dir[0] / 2][col + dir[1] / 2] = PATH;
//            }
//        }
//    }
//
//    private static boolean isWallValid(int row, int col) {
//        if (!isInBounds(row, col) || maze[row][col] == PATH) return false;
//        int count = 0;
//        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        for (int[] dir : directions) {
//            int newRow = row + dir[0];
//            int newCol = col + dir[1];
//            if (isInBounds(newRow, newCol) && maze[newRow][newCol] == PATH) {
//                count++;
//            }
//        }
//        return count == 1;
//    }
//
//    private static boolean isInBounds(int row, int col) {
//        return row > 0 && row < ROWS - 1 && col > 0 && col < COLUMNS - 1;
//    }
//}
//
