package projet20483D;

import java.util.Random;

public class RandomDeplacement implements DeplacementStrategy {

    @Override

    public Direction next() {
        Direction[] directionspossibles = Direction.values();
        Random ra = new Random();
        return directionspossibles[ra.nextInt(6)];
    }
}