package engine;

import model.Player;
import rules.Rule;

public class RuleEngine {
    private final Rule sixRule;
    private final Rule movementRule;
    private final Rule cellEffectRule;

    public RuleEngine(Rule sixRule, Rule movementRule, Rule cellEffectRule) {
        this.sixRule = sixRule;
        this.movementRule = movementRule;
        this.cellEffectRule = cellEffectRule;
    }

    public TurnContext processTurn(GameState state, Player player, int roll) {
        TurnContext context = new TurnContext(player, roll, player.getPosition());

        sixRule.apply(state, context);

        if (!context.isMoveCancelled()) {
            movementRule.apply(state, context);
            player.setPosition(context.getTargetPosition());
            cellEffectRule.apply(state, context);
        }

        if (player.getPosition() == state.getBoard().getSize()) {
            state.markWinner(player);
        }

        return context;
    }

    public static class TurnContext {
        private final Player player;
        private final int roll;
        private final int startPosition;
        private int targetPosition;
        private boolean moveCancelled;
        private boolean extraTurn;

        public TurnContext(Player player, int roll, int startPosition) {
            this.player = player;
            this.roll = roll;
            this.startPosition = startPosition;
            this.targetPosition = startPosition;
            this.moveCancelled = false;
            this.extraTurn = false;
        }

        public Player getPlayer() {
            return player;
        }

        public int getRoll() {
            return roll;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public int getTargetPosition() {
            return targetPosition;
        }

        public void setTargetPosition(int targetPosition) {
            this.targetPosition = targetPosition;
        }

        public boolean isMoveCancelled() {
            return moveCancelled;
        }

        public void setMoveCancelled(boolean moveCancelled) {
            this.moveCancelled = moveCancelled;
        }

        public boolean isExtraTurn() {
            return extraTurn;
        }

        public void setExtraTurn(boolean extraTurn) {
            this.extraTurn = extraTurn;
        }
    }
}
