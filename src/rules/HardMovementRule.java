package rules;

import engine.GameState;
import engine.RuleEngine;

public class HardMovementRule implements Rule {
    @Override
    public void apply(GameState state, RuleEngine.TurnContext context) {
        if (context.isMoveCancelled()) {
            return;
        }

        int boardSize = state.getBoard().getSize();
        int tentative = context.getStartPosition() + context.getRoll();
        if (tentative <= boardSize) {
            context.setTargetPosition(tentative);
            return;
        }

        int overflow = tentative - boardSize;
        context.setTargetPosition(boardSize - overflow);
    }
}
