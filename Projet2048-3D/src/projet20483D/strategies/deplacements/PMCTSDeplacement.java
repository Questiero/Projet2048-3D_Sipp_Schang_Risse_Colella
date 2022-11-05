package projet20483D.strategies.deplacements;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet20483D.Direction;
import projet20483D.Grille3D;
import projet20483D.Parametres;

public class PMCTSDeplacement implements DeplacementStrategy, Parametres {

    private Grille3D grille;
    private final int runs;

    public PMCTSDeplacement(Grille3D grille) {

        try {
            this.grille = grille.deepCopy();
        } catch (IOException ex) {
            Logger.getLogger(PMCTSDeplacement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PMCTSDeplacement.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.runs = 10;
    }

    public PMCTSDeplacement(Grille3D grille, int runs) {

        try {
            this.grille = grille.deepCopy();
        } catch (IOException ex) {
            Logger.getLogger(PMCTSDeplacement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PMCTSDeplacement.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.runs = runs;

    }

    @Override
    public Direction next() {

        Direction maxDir = Direction.UP;
        double maxAverageScore = 0;

        // Average all score for all directions
        for (Direction dir : Direction.values()) {

            int[] scores = new int[this.runs];

            for (int i = 0; i < this.runs; i++) {

                Grille3D tempGrille = null;
                try {
                    tempGrille = grille.deepCopy();
                } catch (IOException ex) {
                    Logger.getLogger(PMCTSDeplacement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PMCTSDeplacement.class.getName()).log(Level.SEVERE, null, ex);
                }

                boolean b = tempGrille.nouvelleCase();

                Direction processDirection = dir;
                
                while (!tempGrille.partieFinie()) {

                    processDirection = Direction.random();

                    boolean b2 = tempGrille.lanceurDeplacerCases(processDirection);
                    if (b2) {
                        b = tempGrille.nouvelleCase();
                    }

                }

                scores[i] = tempGrille.getScore();

            }

            double sumOfScores = 0;

            for (int score : scores) {
                sumOfScores += score;
            }
            double tempAverageScore = sumOfScores / this.runs;

            if (maxAverageScore == 0 || tempAverageScore > maxAverageScore) {
                maxDir = dir;
                maxAverageScore = tempAverageScore;
            }

        }

        return maxDir;

    }

}
