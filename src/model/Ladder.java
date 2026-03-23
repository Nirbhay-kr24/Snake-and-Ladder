package model;

public class Ladder {
    private final int start;
    private final int top;

    public Ladder(int start, int top) {
        if (top <= start) {
            throw new IllegalArgumentException("Ladder top must be greater than start");
        }
        this.start = start;
        this.top = top;
    }

    public int getStart() {
        return start;
    }

    public int getTop() {
        return top;
    }
}
