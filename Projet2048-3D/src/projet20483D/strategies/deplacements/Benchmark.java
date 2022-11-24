package projet20483D.strategies.deplacements;

import java.text.DecimalFormat;
import java.util.Scanner;
import projet20483D.Direction;
import projet20483D.Grille3D;
import projet20483D.strategies.deplacements.ExpectimaxDeplacement.ExpectimaxType;

public class Benchmark {

    public static void main(String[] args) {

        long initTime = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        int sumOfScores = 0;
        int nbrOfWins = 0;

        int nbrTests = 1000;

        for (int i = 0; i < nbrTests; i++) {

            Grille3D g = new Grille3D();
            boolean b = g.nouvelleCase();

            // DeplacementContext context = new DeplacementContext(new RandomDeplacement());
            DeplacementContext context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxType.FULLBLAST));

            int n = 0;

            while (!g.partieFinie()) {

                Direction dir = context.executeStrategy();
                boolean b2 = g.lanceurDeplacerCases(dir);
                /*System.out.println("-----------");
                System.out.println(g);
                System.out.println(dir);*/
                if (b2) {
                    b = g.nouvelleCase();
                    //System.out.println(g.getScore());
                    n++;
                }

            }

            sumOfScores += g.getScore();
            if (g.isVictory()) {
                nbrOfWins++;
            }

            System.out.println("[" + ((System.currentTimeMillis() - initTime)) + "ms | " + (i + 1) + "/" + nbrTests + " | n = " + n + " | " + df.format(((double) nbrOfWins) * 100 / (i + 1)) + "% ] " + g.gameOverMessage());

        }

        System.out.println("Score moyen: " + sumOfScores / nbrTests + " | Nombre de victoires: " + nbrOfWins + "/" + nbrTests + " | Pourcentage de victoires: " + df.format(((double) nbrOfWins) * 100 / nbrTests) + "%");

    }

}
