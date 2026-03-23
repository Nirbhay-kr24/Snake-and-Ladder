package model;

import effect.CellEffect;
import java.util.Optional;

public class Cell {
    private final int number;
    private CellEffect effect;
    private Player occupant;

    public Cell(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Optional<CellEffect> getEffect() {
        return Optional.ofNullable(effect);
    }

    public void setEffect(CellEffect effect) {
        this.effect = effect;
    }

    public Optional<Player> getOccupant() {
        return Optional.ofNullable(occupant);
    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public void occupy(Player player) {
        this.occupant = player;
    }

    public void vacate() {
        this.occupant = null;
    }
}
