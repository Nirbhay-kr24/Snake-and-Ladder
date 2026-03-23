package rules;

import engine.GameState;
import engine.RuleEngine;

public interface Rule {
    void apply(GameState state, RuleEngine.TurnContext context);
}
