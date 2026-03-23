package effect;

import model.Player;

public class LadderEffect implements CellEffect {
    private final int from;
    private final int to;

    public LadderEffect(int from, int to) {
        if (to <= from) {
            throw new IllegalArgumentException("Ladder top must be above its base");
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
        return "Ladder: " + from + " -> " + to;
    }
}
