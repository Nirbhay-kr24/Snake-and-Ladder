package engine;

import dice.Dice;
import event.EventType;
import event.GameEvent;
import model.Board;
import model.Ladder;
import model.Player;
import model.Snake;

public class Game {
    private final GameState state;
    private final Dice dice;
    private final RuleEngine ruleEngine;
    public Game(GameState state, Dice dice) {
        this.state = state;
        this.dice = dice;
        this.ruleEngine = null;
    }

    public Game(GameState state, Dice dice, RuleEngine ruleEngine) {
        this.state = state;
        this.dice = dice;
        this.ruleEngine = ruleEngine;
    }

    public void makeMove(Player player) {
        if (state.isFinished()) {
            return;
        }
        if (player != state.getCurrentPlayer()) {
            throw new IllegalArgumentException("It is not " + player.getName() + " turn");
        }

        Board board = state.getBoard();
        int startPosition = player.getPosition();
        state.log(EventType.TURN_STARTED, player.getName() + " turn started at " + startPosition);

        int roll = dice.roll();
        state.log(EventType.DICE_ROLLED, player.getName() + " rolled " + roll);

        int destination = Math.min(startPosition + roll, board.getTotalCells());

        if (destination > 0 && board.getCell(destination).isOccupied()) {
            state.log(EventType.TURN_SKIPPED,
                    player.getName() + " cannot move to " + destination + " because cell is occupied");
            state.advanceTurn();
            return;
        }

        if (startPosition > 0) {
            board.getCell(startPosition).vacate();
        }

        player.setPosition(destination);
        if (destination > 0) {
            board.getCell(destination).occupy(player);
        }
        state.log(EventType.MOVED, player.getName() + " moved from " + startPosition + " to " + destination);

        applySnakeOrLadder(player);

        if (player.getPosition() == board.getTotalCells()) {
            state.markWinner(player);
            state.log(EventType.WINNER_DECIDED, player.getName() + " won the game");
            return;
        }

        state.advanceTurn();
    }

    private void applySnakeOrLadder(Player player) {
        Board board = state.getBoard();
        int current = player.getPosition();

        Snake snake = board.getSnakeAt(current).orElse(null);
        if (snake != null) {
            moveThroughEffect(player, snake.getTail(), "snake");
            return;
        }

        Ladder ladder = board.getLadderAt(current).orElse(null);
        if (ladder != null) {
            moveThroughEffect(player, ladder.getTop(), "ladder");
        }
    }

    private void moveThroughEffect(Player player, int target, String effectName) {
        Board board = state.getBoard();
        int from = player.getPosition();

        if (board.getCell(target).isOccupied()) {
            state.log(EventType.TURN_SKIPPED,
                    player.getName() + " hit a " + effectName + " at " + from + " but " + target + " is occupied");
            return;
        }

        board.getCell(from).vacate();
        player.setPosition(target);
        board.getCell(target).occupy(player);
        state.log(EventType.EFFECT_APPLIED,
                player.getName() + " moved via " + effectName + " from " + from + " to " + target);
    }

    public void play() {
        while (!state.isFinished()) {
            makeMove(state.getCurrentPlayer());
        }
    }

    public void printEventLog() {
        for (GameEvent event : state.getEvents()) {
            System.out.println(event);
        }
    }

    public GameState getState() {
        return state;
    }
}
