package factory;

import rules.CellEffectRule;
import rules.EasyMovementRule;
import rules.HardMovementRule;
import rules.InfiniteSixRule;
import rules.LimitedSixRule;
import rules.Rule;

public final class RuleFactory {
    private RuleFactory() {
    }

    public static Rule createSixRule(boolean limited) {
        return limited ? new LimitedSixRule() : new InfiniteSixRule();
    }

    public static Rule createMovementRule(boolean hardBounceBack) {
        return hardBounceBack ? new HardMovementRule() : new EasyMovementRule();
    }

    public static Rule createCellEffectRule() {
        return new CellEffectRule();
    }
}
