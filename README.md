
MazeGame is a simple yet engaging JavaFX-based game where the player navigates through a randomly generated maze, collects items (coins and diamonds) to increase their score, and reaches the exit door to complete the game. The maze is generated using a recursive backtracking algorithm, ensuring a unique layout for every playthrough.

Random Maze Generation : A new maze is generated for each game session using a recursive backtracking algorithm.
Player Movement : Control the snake-like player using keyboard inputs to navigate the maze.
Collectibles : Collect coins and diamonds scattered throughout the maze to increase your score.
Timer : A countdown timer tracks how long the player takes to complete the maze.
Exit Door : Reach the door located in the bottom-right corner of the maze to finish the game.
Start Window : A simple start window with a "Start" button initiates the game.
Classes
1. MazeGame (Main Class)
The entry point of the program. It initializes the start window and handles transitions to the main game window.

2. MazeGenerator
Responsible for generating a random maze using the recursive backtracking algorithm. This class ensures that the maze is solvable and has a unique layout for every run.

3. MazeLayout
Holds the characteristics of the maze, such as its dimensions, walls, paths, and the positions of collectibles and the exit door.

4. Player
Handles the player's movement within the maze. The player is represented as a snake-like entity that can move up, down, left, or right using keyboard inputs.

5. Collectible
Represents the items (coins and diamonds) scattered throughout the maze. Collecting these increases the player's score.

How to Play
Launch the application.
On the start window, click the "Start" button to begin the game.
Use the arrow keys (↑, ↓, ←, →) to control the player's movement.
Navigate through the maze and collect coins and diamonds to increase your score.
Keep an eye on the timer in the top-left corner.
Reach the exit door located in the bottom-right corner of the maze to complete the game.
Installation
Prerequisites
Java Development Kit (JDK) 8 or higher
JavaFX SDK installed and configured
IDE such as IntelliJ IDEA, Eclipse, or any text editor with Java support
Steps
Clone the repository:
git clone https://github.com/cristi504/MazeGame.git
Open the project in your preferred IDE.
Ensure JavaFX is properly configured in your IDE.
Build and run the project.
Usage
Running the Game
Compile and run the MazeGame class.
The start window will appear. Click the "Start" button to begin the game.


