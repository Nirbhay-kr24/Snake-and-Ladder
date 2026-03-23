package snakeladder.service;

import snakeladder.enums.ObstacleType;
import snakeladder.factory.ObstacleFactory;
import snakeladder.model.Board;
import snakeladder.model.Dice;
import snakeladder.model.Player;

import java.util.*;

public class Game {
    private final int n;
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private final String difficulty;

    public Game(int n, String difficulty) {
        this.n = n;
        this.difficulty = difficulty;
        this.board = new Board(n * n);
        this.dice = new Dice(1);
        this.players = new ArrayDeque<>();

        initBoardObstacles();
    }

    private void initBoardObstacles() {

        generateObstacles(n, ObstacleType.SNAKE);
        generateObstacles(n, ObstacleType.LADDER);
    }

    private void generateObstacles(int count, ObstacleType type) {
        Random random = new Random();
        int maxPos = board.getSize();

        while (count > 0) {

            int v1 = random.nextInt(maxPos - 2) + 2;
            int v2 = random.nextInt(maxPos - 2) + 2;

            if (v1 == v2 || Math.abs(v1 - v2) < 5) continue;

            int start, end;
            if (type == ObstacleType.LADDER) {

                start = Math.min(v1, v2);
                end = Math.max(v1, v2);


                if (difficulty.equalsIgnoreCase("easy") && (end - start) < 10) continue;
            } else {

                start = Math.max(v1, v2);
                end = Math.min(v1, v2);
            }


            if (board.addObstacle(ObstacleFactory.createObstacle(type, start, end))) {
                count--;
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {

        System.out.println("\n--- INITIAL BOARD CONFIGURATION (Snakes & Ladders) ---");
        board.printBoard(players);
        System.out.println("-------------------------------------------------------\n");


        while (players.size() > 1) {
            Player currPlayer = players.poll();
            int roll = dice.roll();


            int newPosition = board.getNewPosition(currPlayer, roll);

            if (newPosition == board.getSize()) {
                System.out.println("🏆 " + currPlayer.getName() + " reached " + board.getSize() + " and FINISHED!");
            } else {
                currPlayer.setPosition(newPosition);
                players.offer(currPlayer);
            }
        }

        System.out.println("\n--- GAME OVER ---");
        Player loser = players.poll();
        if (loser != null) {
            System.out.println("The final player (loser) is: " + loser.getName());
        }
    }
}