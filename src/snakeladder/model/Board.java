package snakeladder.model;

import lombok.Getter;
import snakeladder.enums.ObstacleType;


import java.util.Queue;

@Getter
public class Board {
    private final int size; // This is n*n
    private final int sideLength; // This is n
    private final Cell[][] grid;

    public Board(int size) {
        this.size = size;
        this.sideLength = (int) Math.sqrt(size);
        this.grid = new Cell[sideLength][sideLength];
        initializeGrid();
    }

    private void initializeGrid() {
        int position = 1;
        boolean leftToRight = true;


        for (int i = sideLength - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < sideLength; j++) {
                    grid[i][j] = new Cell(position++);
                }
            } else {
                for (int j = sideLength - 1; j >= 0; j--) {
                    grid[i][j] = new Cell(position++);
                }
            }
            leftToRight = !leftToRight;
        }
    }


    private Cell getCell(int position) {
        int row = (position - 1) / sideLength;
        int col = (position - 1) % sideLength;


        if (row % 2 != 0) {
            col = (sideLength - 1) - col;
        }

        int actualRow = (sideLength - 1) - row;
        return grid[actualRow][col];
    }

    public boolean addObstacle(Obstacle obstacle) {

        int src = obstacle.getSrc();

        Cell srcCell = getCell(src);

        if (srcCell.hasObstacle()) return false;

        srcCell.setObstacle(obstacle);
        return true;
    }


    public int getNewPosition(Player player, int roll) {
        int currentPos = player.getPosition();
        int nextPos = currentPos + roll;


        if (nextPos > size) {
            System.out.println(player.getName() + " rolled " + roll + " but stays at " + currentPos + " (Limit Exceeded)");
            return currentPos;
        }

        Cell cell = getCell(nextPos);
        int finalPosition = cell.getFinalPosition();


        if (finalPosition < nextPos) {
            System.out.println("🐍 SNAKE! " + player.getName() + " fell from " + nextPos + " to " + finalPosition);
        } else if (finalPosition > nextPos) {
            System.out.println("🪜 LADDER! " + player.getName() + " climbed from " + nextPos + " to " + finalPosition);
        } else {
            System.out.println(player.getName() + " moved to " + nextPos);
        }

        return finalPosition;
    }

    public void printBoard(Queue<Player> players) {
        System.out.println("\n--- Current Board State ---");
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                Cell cell = grid[i][j];
                String display = String.valueOf(cell.getPosition());


                for (Player p : players) {
                    if (p.getPosition() == cell.getPosition()) {
                        display = "[" + p.getName() + "]";
                        break;
                    }
                }


                // printBoard
                if (display.chars().allMatch(Character::isDigit) && cell.hasObstacle()) {
                    Obstacle o = cell.getObstacle();

                    if (o.getObstacleType() == ObstacleType.LADDER) {
                        display = "L" + o.getDest();
                    } else {
                        display = "S" + o.getDest();
                    }
                }

                System.out.printf("%-10s", display);
            }
            System.out.println();
        }
    }
}