package snakeladder.model;

import lombok.Getter;
import snakeladder.enums.ObstacleType;

@Getter
public abstract class Obstacle {
    protected int src;
    protected int dest;

    public Obstacle(int src, int dest){
        this.src = src;
        this.dest = dest;
    }

    public int movePlayer(){
        return dest;
    }

    public abstract ObstacleType getObstacleType();
}