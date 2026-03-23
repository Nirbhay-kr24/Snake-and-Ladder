package rules;

import engine.GameState;
import engine.RuleEngine;

public class LimitedSixRule implements Rule {
    private static final int MAX_ALLOWED_SIXES = 2;

    @Override
    public void apply(GameState state, RuleEngine.TurnContext context) {
        if (context.getRoll() != 6) {
            context.getPlayer().resetConsecutiveSixes();
            context.setExtraTurn(false);
            return;
        }

        context.getPlayer().incrementConsecutiveSixes();
        int streak = context.getPlayer().getConsecutiveSixes();

        if (streak > MAX_ALLOWED_SIXES) {
            context.setMoveCancelled(true);
            context.setTargetPosition(context.getStartPosition());
            context.getPlayer().resetConsecutiveSixes();
            context.setExtraTurn(false);
            return;
        }

        context.setExtraTurn(true);
    }
}
