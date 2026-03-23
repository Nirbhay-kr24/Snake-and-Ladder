package model;

public class PlayerImpl implements Player {
    private final String name;
    private int position;
    private int consecutiveSixes;

    public PlayerImpl(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank");
        }
        this.name = name;
        this.position = 0;
        this.consecutiveSixes = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getConsecutiveSixes() {
        return consecutiveSixes;
    }

    @Override
    public void incrementConsecutiveSixes() {
        this.consecutiveSixes++;
    }

    @Override
    public void resetConsecutiveSixes() {
        this.consecutiveSixes = 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", consecutiveSixes=" + consecutiveSixes +
                '}';
    }
}
