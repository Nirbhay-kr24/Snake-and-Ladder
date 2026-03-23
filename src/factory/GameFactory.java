package factory;

import dice.Dice;
import dice.DiceImpl;
import engine.Game;
import engine.GameManager;
import engine.GameState;
import java.util.ArrayList;
import java.util.List;
import model.Board;
import model.Difficulty;
import model.Player;
import model.PlayerImpl;

public final class GameFactory {
    private GameFactory() {
    }

    public static GameManager createGame(int n, int playerCount, String difficultyLabel) {
        Difficulty difficulty = Difficulty.fromLabel(difficultyLabel);
        return createGame(n, playerCount, difficulty);
    }

    public static GameManager createGame(int n, int playerCount, Difficulty difficulty) {
        if (playerCount < 2) {
            throw new IllegalArgumentException("At least 2 players are required");
        }

        Board board = new Board(n);
        CellEffectFactory.populateBoard(board, difficulty);

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            players.add(new PlayerImpl("P" + i));
        }

        GameState state = new GameState(board, players);
        Dice dice = new DiceImpl(6);
        Game game = new Game(state, dice);

        return new GameManager(game);
    }
}
