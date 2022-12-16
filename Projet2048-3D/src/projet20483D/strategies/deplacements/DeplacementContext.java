package projet20483D.strategies.deplacements;

import projet20483D.Direction;

/**
 * Classe de context (patron de conception) permettant un déplacement
 */
public class DeplacementContext {

    private DeplacementStrategy strategy;

    /**
     * Crée un context en fonction d'une stratégie
     *
     * @param s Stratégie
     */
    public DeplacementContext(DeplacementStrategy s) {
        this.strategy = s;
    }

    /**
     * Exécute la stratégie
     *
     * @return Direction donnée par la stratégie
     */
    public Direction executeStrategy() {
        return this.strategy.next();
    }

    /**
     * Configure la stratégie
     *
     * @param s Stratégie
     */
    public void setStrategy(DeplacementStrategy s) {
        this.strategy = s;
    }
}
