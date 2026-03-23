package factory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import model.Board;
import model.Difficulty;
import model.Ladder;
import model.Snake;

public final class CellEffectFactory {
    private CellEffectFactory() {
    }

    public static void populateBoard(Board board, Difficulty difficulty) {
        populateBoard(board, difficulty, new Random());
    }

    public static void populateBoard(Board board, Difficulty difficulty, Random random) {
        int total = board.getTotalCells();
        Set<Integer> effectStartCells = new HashSet<>();
        effectStartCells.add(total);

        placeSnakes(board, difficulty.getSnakeCount(), random, effectStartCells);
        placeLadders(board, difficulty.getLadderCount(), random, effectStartCells);
    }

    private static void placeSnakes(Board board, int count, Random random, Set<Integer> usedStartCells) {
        int placed = 0;
        int maxAttempts = 10_000;

        while (placed < count && maxAttempts-- > 0) {
            int head = randomInRange(random, 2, board.getTotalCells() - 1);
            if (usedStartCells.contains(head)) {
                continue;
            }

            int tail = randomInRange(random, 1, head - 1);
            board.placeSnake(new Snake(head, tail));
            usedStartCells.add(head);
            placed++;
        }

        if (placed < count) {
            throw new IllegalStateException("Unable to place all snakes on the board");
        }
    }

    private static void placeLadders(Board board, int count, Random random, Set<Integer> usedStartCells) {
        int placed = 0;
        int maxAttempts = 10_000;

        while (placed < count && maxAttempts-- > 0) {
            int start = randomInRange(random, 1, board.getTotalCells() - 1);
            if (usedStartCells.contains(start)) {
                continue;
            }

            int top = randomInRange(random, start + 1, board.getTotalCells());
            board.placeLadder(new Ladder(start, top));
            usedStartCells.add(start);
            placed++;
        }

        if (placed < count) {
            throw new IllegalStateException("Unable to place all ladders on the board");
        }
    }

    private static int randomInRange(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
