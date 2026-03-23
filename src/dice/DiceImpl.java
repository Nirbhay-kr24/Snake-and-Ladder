package dice;

import java.util.Random;

public class DiceImpl implements Dice {
    private final int sides;
    private final Random random;

    public DiceImpl(int sides) {
        if (sides < 2) {
            throw new IllegalArgumentException("Dice needs at least 2 sides");
        }
        this.sides = sides;
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }
}
