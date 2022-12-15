package projet20483D;

import java.util.Random;

/**
 * Enumération représentant une direction dans la grille
 */
public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT,
    BACK,
    FRONT;

    /**
     * Retourne la Direction opposée
     * @return Direction opposée
     */
    public Direction opposite() {

        switch (this) {
            case UP:
                return DOWN;
            case LEFT:
                return RIGHT;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
            case BACK:
                return FRONT;
            case FRONT:
                return BACK;
        }

        return null;

    }

    /**
     * Retourne une direction aléatoire
     * @return Direction aléatoire
     */
    public static Direction random() {
        Direction[] directionsPossibles = Direction.values();
        Random ra = new Random();
        return directionsPossibles[ra.nextInt(directionsPossibles.length)];
    }

}
