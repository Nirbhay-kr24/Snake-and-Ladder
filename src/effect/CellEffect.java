package effect;

import model.Player;

public interface CellEffect {
    int apply(Player player);

    String describe();
}
