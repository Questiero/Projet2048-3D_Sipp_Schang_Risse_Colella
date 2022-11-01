package projet20483D.strategies.deplacements;

import projet20483D.Direction;

public class DeplacementContext {

    private DeplacementStrategy strategy;

    public DeplacementContext(DeplacementStrategy s) {
        this.strategy = s;
    }

    public Direction executeStrategy() {
        return this.strategy.next();
    }

    public void setStrategy(DeplacementStrategy s) {
        this.strategy = s;
    }
}
