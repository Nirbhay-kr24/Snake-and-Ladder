package model;

public enum Difficulty {
    EASY(4, 4),
    MEDIUM(6, 6),
    HARD(8, 4);

    private final int snakeCount;
    private final int ladderCount;

    Difficulty(int snakeCount, int ladderCount) {
        this.snakeCount = snakeCount;
        this.ladderCount = ladderCount;
    }

    public int getSnakeCount() {
        return snakeCount;
    }

    public int getLadderCount() {
        return ladderCount;
    }

    public static Difficulty fromLabel(String label) {
        if (label == null) {
            return MEDIUM;
        }
        return switch (label.trim().toUpperCase()) {
            case "EASY" -> EASY;
            case "HARD" -> HARD;
            default -> MEDIUM;
        };
    }
}
