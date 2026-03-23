package event;

import java.time.LocalDateTime;

public class GameEvent {
    private final LocalDateTime timestamp;
    private final EventType type;
    private final String message;

    public GameEvent(EventType type, String message) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public EventType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + " - " + message;
    }
}
