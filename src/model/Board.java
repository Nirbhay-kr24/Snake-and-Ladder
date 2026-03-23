package model;

import effect.CellEffect;
import effect.LadderEffect;
import effect.SnakeEffect;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    private final int dimension;
    private final int totalCells;
    private final Map<Integer, Cell> cells;
    private final Map<Integer, Snake> snakeByHead;
    private final Map<Integer, Ladder> ladderByStart;

    public Board(int dimension) {
        if (dimension < 2) {
            throw new IllegalArgumentException("Board dimension must be at least 2");
        }
        this.dimension = dimension;
        this.totalCells = dimension * dimension;
        this.cells = new HashMap<>();
        this.snakeByHead = new HashMap<>();
        this.ladderByStart = new HashMap<>();
        for (int i = 1; i <= totalCells; i++) {
            cells.put(i, new Cell(i));
        }
    }

    public int getDimension() {
        return dimension;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public int getSize() {
        return totalCells;
    }

    public Cell getCell(int position) {
        validatePosition(position);
        return cells.get(position);
    }

    public void placeEffect(int position, CellEffect effect) {
        validatePosition(position);
        cells.get(position).setEffect(effect);
    }

    public Optional<CellEffect> getEffectAt(int position) {
        validatePosition(position);
        return cells.get(position).getEffect();
    }

    public void placeSnake(Snake snake) {
        if (snake.getHead() == totalCells) {
            throw new IllegalArgumentException("Snake cannot start on last cell");
        }
        validatePosition(snake.getHead());
        validatePosition(snake.getTail());
        if (snake.getHead() <= snake.getTail()) {
            throw new IllegalArgumentException("Snake head must be above tail");
        }
        ensureNoExistingEffect(snake.getHead());

        snakeByHead.put(snake.getHead(), snake);
        cells.get(snake.getHead()).setEffect(new SnakeEffect(snake.getHead(), snake.getTail()));
    }

    public void placeLadder(Ladder ladder) {
        if (ladder.getStart() == totalCells) {
            throw new IllegalArgumentException("Ladder cannot start on last cell");
        }
        validatePosition(ladder.getStart());
        validatePosition(ladder.getTop());
        if (ladder.getTop() <= ladder.getStart()) {
            throw new IllegalArgumentException("Ladder top must be above start");
        }
        ensureNoExistingEffect(ladder.getStart());

        ladderByStart.put(ladder.getStart(), ladder);
        cells.get(ladder.getStart()).setEffect(new LadderEffect(ladder.getStart(), ladder.getTop()));
    }

    public Optional<Snake> getSnakeAt(int position) {
        validatePosition(position);
        return Optional.ofNullable(snakeByHead.get(position));
    }

    public Optional<Ladder> getLadderAt(int position) {
        validatePosition(position);
        return Optional.ofNullable(ladderByStart.get(position));
    }

    private void ensureNoExistingEffect(int position) {
        if (cells.get(position).getEffect().isPresent()) {
            throw new IllegalArgumentException("Cell already has a snake or ladder: " + position);
        }
    }

    private void validatePosition(int position) {
        if (position < 1 || position > totalCells) {
            throw new IllegalArgumentException("Position out of board range: " + position);
        }
    }
}
