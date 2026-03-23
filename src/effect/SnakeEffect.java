package effect;

import model.Player;

public class SnakeEffect implements CellEffect {
    private final int from;
    private final int to;

    public SnakeEffect(int from, int to) {
        if (to >= from) {
            throw new IllegalArgumentException("Snake tail must be below its head");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public int apply(Player player) {
        return to;
    }

    @Override
    public String describe() {
        return "Snake: " + from + " -> " + to;
    }
}
