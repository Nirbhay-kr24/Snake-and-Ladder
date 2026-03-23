package rules;

import engine.GameState;
import engine.RuleEngine;

public class InfiniteSixRule implements Rule {
    @Override
    public void apply(GameState state, RuleEngine.TurnContext context) {
        if (context.getRoll() == 6) {
            context.getPlayer().incrementConsecutiveSixes();
            context.setExtraTurn(true);
            return;
        }

        context.getPlayer().resetConsecutiveSixes();
        context.setExtraTurn(false);
    }
}
