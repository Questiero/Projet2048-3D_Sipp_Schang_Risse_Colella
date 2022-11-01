package projet20483D.strategies.deplacements;

import java.util.Random;
import projet20483D.Direction;

public class RandomDeplacementStrategy implements DeplacementStrategy {

    @Override
    public Direction next() {
        Direction[] directionspossibles = Direction.values();
        Random ra = new Random();
        return directionspossibles[ra.nextInt(6)];
    }
    
}