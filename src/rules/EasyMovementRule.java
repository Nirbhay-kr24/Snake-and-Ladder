package rules;

import engine.GameState;
import engine.RuleEngine;

public class EasyMovementRule implements Rule {
    @Override
    public void apply(GameState state, RuleEngine.TurnContext context) {
        if (context.isMoveCancelled()) {
            return;
        }

        int boardSize = state.getBoard().getSize();
        int tentative = context.getStartPosition() + context.getRoll();
        context.setTargetPosition(Math.min(tentative, boardSize));
    }
}
