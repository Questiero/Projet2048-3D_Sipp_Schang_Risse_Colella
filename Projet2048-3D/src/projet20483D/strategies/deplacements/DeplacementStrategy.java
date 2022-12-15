package projet20483D.strategies.deplacements;

import projet20483D.Direction;

/**
 * Stratégie de déplacement
 */
public interface DeplacementStrategy {
    
    /**
     * Exécution de la stratégie
     * @return 
     */
    public Direction next();
    
}