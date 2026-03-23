package engine;

import event.EventType;
import event.GameEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import model.Board;
import model.Player;

public class GameState {
    private final Board board;
    private final List<Player> players;
    private final Deque<Player> turnOrder;
    private final List<GameEvent> events;
    private Player winner;
    private boolean finished;

    public GameState(Board board, List<Player> players) {
        if (players == null || players.size() < 2) {
            throw new IllegalArgumentException("At least two players are required");
        }
        this.board = board;
        this.players = List.copyOf(players);
        this.turnOrder = new ArrayDeque<>(players);
        this.events = new ArrayList<>();
        this.finished = false;

        for (Player player : this.players) {
            player.setPosition(0);
            player.resetConsecutiveSixes();
        }
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getCurrentPlayer() {
        return turnOrder.peekFirst();
    }

    public void advanceTurn() {
        Player current = turnOrder.pollFirst();
        if (current != null) {
            turnOrder.offerLast(current);
        }
    }

    public void markWinner(Player winner) {
        this.winner = winner;
        this.finished = true;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isFinished() {
        return finished;
    }

    public void log(EventType type, String message) {
        events.add(new GameEvent(type, message));
    }

    public List<GameEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }
}
