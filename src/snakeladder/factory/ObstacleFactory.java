package snakeladder.factory;

import snakeladder.enums.ObstacleType;
import snakeladder.model.Ladder;
import snakeladder.model.Obstacle;
import snakeladder.model.Snake;

public class ObstacleFactory {
    public static Obstacle createObstacle(ObstacleType type, int up, int down) {
        return switch (type) {
            case SNAKE -> new Snake(up, down);
            case LADDER -> new Ladder(up, down);
            default -> throw new IllegalArgumentException("Invalid obstacle type");
        };
    }
}