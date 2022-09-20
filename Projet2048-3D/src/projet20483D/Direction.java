package projet20483D;

public enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT,
    BACK,
    FRONT;
    
    public Direction opposite() {
        
        switch(this) {
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
    
}
