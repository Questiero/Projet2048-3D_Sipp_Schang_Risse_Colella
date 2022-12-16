package projet20483D.strategies.deplacements;

import projet20483D.Direction;

/**
 * Stratégie de déplacement aléatoire
 */
public class RandomDeplacement implements DeplacementStrategy {

    @Override
    public Direction next() {
        return Direction.random();
    }

}
