package snakeladder;

import snakeladder.model.Player;
import snakeladder.service.Game;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board dimension (n for nxn board): ");
        int n = scanner.nextInt();

        System.out.print("Enter the number of players: ");
        int noOfPlayers = scanner.nextInt();

        System.out.print("Enter difficulty level (easy/hard): ");
        String difficulty = scanner.next().toLowerCase();

        //System.out.print("Enter the number of Dice: ");
        // int noOfDice = scanner.nextInt();

        Game game = new Game(n, difficulty);
        // Game game = new Game(n, noOfDice, difficulty);
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.print("Enter the name of player " + (i + 1) + " : ");
            game.addPlayer(new Player(scanner.next()));
        }

        game.startGame();
    }
}