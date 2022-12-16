package projet20483D.strategies.deplacements;

import java.text.DecimalFormat;
import java.util.Scanner;
import projet20483D.Direction;
import projet20483D.Grille3D;
import projet20483D.strategies.deplacements.ExpectimaxDeplacement.ExpectimaxType;

/**
 * Classe permettant l'évaluation des chances de gagner des IA
 */
public class Benchmark {

    /**
     * Fonction permettant le lancement d'un benchmark
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nCombien de tests souhaitez-vous réaliser ?");
        int input = sc.nextInt();
        while (input < 1) {
            System.out.println("\nErreur à l'entrée");

            System.out.println("\nCombien de tests souhaitez-vous réaliser ?");

            input = sc.nextInt();

        }
        int nbrTests = input;

        System.out.println("\nQuelle profondeur maximum souhaitez-vous utiliser ?");
        input = sc.nextInt();
        while (input < 1) {
            System.out.println("\nErreur à l'entrée");
            System.out.println("\nQuelle profondeur maximum souhaitez-vous utiliser ?");
            input = sc.nextInt();

        }
        int depth = input;

        System.out.println("\nQuelle fonction d'évaluation souhaitez-vous utiliser ?");
        System.out.println("1. Naive");
        System.out.println("2. Cases vides uniquement");
        System.out.println("3. Avancée");
        input = sc.nextInt();
        ExpectimaxType type = ExpectimaxType.NAIVE;
        while (input < 1 && input > 3) {
            System.out.println("\nErreur à l'entrée");

            System.out.println("\nQuelle fonction d'évaluation souhaitez-vous utiliser ?");
            System.out.println("1. Naive");
            System.out.println("2. Cases vides uniquement");
            System.out.println("3. Avancée");

            input = sc.nextInt();

        }
        switch (input) {
            case 2:
                System.out.println("\nRéalisation de " + nbrTests + " tests sur l'Expectimax utilisant la fonction Cases vides uniquement avec une profondeur de " + depth);
                type = ExpectimaxType.EMPTYONLY;
                break;
            case 3:
                System.out.println("\nRéalisation de " + nbrTests + " tests sur l'Expectimax utilisant la fonction Avancée avec une profondeur de " + depth);
                type = ExpectimaxType.ADVANCED;
                break;
            default:
                System.out.println("\nRéalisation de " + nbrTests + " tests sur l'Expectimax utilisant la fonction Naive avec une profondeur de " + depth);
                type = ExpectimaxType.NAIVE;
        }

        long initTime = System.currentTimeMillis();

        DecimalFormat df = new DecimalFormat("0.00");

        int sumOfScores = 0;
        int nbrOfWins = 0;

        for (int i = 0; i < nbrTests; i++) {

            Grille3D g = new Grille3D();
            boolean b = g.nouvelleCase();

            DeplacementContext context = new DeplacementContext(new ExpectimaxDeplacement(g, depth, type));

            int n = 0;

            while (!g.partieFinie()) {

                Direction dir = context.executeStrategy();
                boolean b2 = g.lanceurDeplacerCases(dir);
                /*System.out.println("-----------");
                System.out.println(g);
                System.out.println(dirdir);*/
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
