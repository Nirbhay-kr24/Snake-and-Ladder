package rules;

import effect.CellEffect;
import engine.GameState;
import engine.RuleEngine;
import event.EventType;
import java.util.Optional;

public class CellEffectRule implements Rule {
    @Override
    public void apply(GameState state, RuleEngine.TurnContext context) {
        if (context.isMoveCancelled()) {
            return;
        }

        Optional<CellEffect> effect = state.getBoard().getEffectAt(context.getTargetPosition());
        if (effect.isEmpty()) {
            return;
        }

        int from = context.getTargetPosition();
        int to = effect.get().apply(context.getPlayer());
        context.setTargetPosition(to);
        context.getPlayer().setPosition(to);
        state.log(EventType.EFFECT_APPLIED,
                context.getPlayer().getName() + " triggered " + effect.get().describe() + " (" + from + " -> " + to + ")");
    }
}
