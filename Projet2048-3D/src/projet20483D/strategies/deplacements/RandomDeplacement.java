package projet20483D.strategies.deplacements;

import projet20483D.Direction;

public class RandomDeplacement implements DeplacementStrategy {

    @Override
    public Direction next() {
        return Direction.random();
    }

}
