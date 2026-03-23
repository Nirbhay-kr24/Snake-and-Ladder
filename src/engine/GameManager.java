package engine;

import event.GameEvent;
import java.util.List;
import model.Player;

public class GameManager {
    private final Game game;

    public GameManager(Game game) {
        this.game = game;
    }

    public void makeMove(Player player) {
        game.makeMove(player);
    }

    public void start() {
        game.play();
    }

    public Player getCurrentPlayer() {
        return game.getState().getCurrentPlayer();
    }

    public boolean isFinished() {
        return game.getState().isFinished();
    }

    public Player getWinner() {
        return game.getState().getWinner();
    }

    public List<GameEvent> getEvents() {
        return game.getState().getEvents();
    }

    public GameState getState() {
        return game.getState();
    }
}
