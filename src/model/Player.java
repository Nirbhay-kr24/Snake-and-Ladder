package model;

public interface Player {
    String getName();

    int getPosition();

    void setPosition(int position);

    int getConsecutiveSixes();

    void incrementConsecutiveSixes();

    void resetConsecutiveSixes();
}
