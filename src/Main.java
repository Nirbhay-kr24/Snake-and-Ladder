import engine.GameManager;
import event.GameEvent;
import factory.GameFactory;
import model.Player;

public class Main {
    public static void main(String[] args) {
        int dimension = getIntArg(args, 0, 10);
        int playerCount = getIntArg(args, 1, 2);
        String difficulty = getStringArg(args, 2, "MEDIUM");

        GameManager gameManager = GameFactory.createGame(dimension, playerCount, difficulty);
        gameManager.start();

        Player winner = gameManager.getWinner();
        if (winner != null) {
            System.out.println("Winner: " + winner.getName() + " at position " + winner.getPosition());
        }

        System.out.println("---- Event Log ----");
        for (GameEvent event : gameManager.getEvents()) {
            System.out.println(event);
        }
    }

    private static int getIntArg(String[] args, int index, int defaultValue) {
        if (index >= args.length) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(args[index]);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    private static String getStringArg(String[] args, int index, String defaultValue) {
        if (index >= args.length) {
            return defaultValue;
        }
        String value = args[index];
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
