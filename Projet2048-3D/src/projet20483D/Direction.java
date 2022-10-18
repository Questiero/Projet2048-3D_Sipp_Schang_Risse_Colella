package projet20483D;

import java.util.Random;

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
    
    public static Direction random(){
        Random ra = new Random();
        int nbRandom = ra.nextInt(6);
                System.out.println(nbRandom);
        
        switch (nbRandom){
            case 0:
                return DOWN;
            case 1:
                return RIGHT;
            case 2:
                return UP;
            case 3:
                return LEFT;
            case 4:
                return FRONT;
            case 5:
                return BACK;
        }

        return null;
        
    }
    
    
    
}
