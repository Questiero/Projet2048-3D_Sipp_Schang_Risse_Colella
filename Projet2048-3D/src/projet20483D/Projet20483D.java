package projet20483D;

import projet20483D.strategies.deplacements.RandomDeplacement;
import java.util.Scanner;
import projet20483D.strategies.deplacements.DeplacementContext;
import projet20483D.strategies.deplacements.ExpectimaxDeplacement;
import projet20483D.strategies.deplacements.PMCTSDeplacement;

public class Projet20483D implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 100; i++) {

            Grille3D g = new Grille3D();
            boolean b = g.nouvelleCase();

            DeplacementContext context = new DeplacementContext(new RandomDeplacement());
            // DeplacementContext context = new DeplacementContext(new PMCTSDeplacement(g, 10));
            // DeplacementContext context = new DeplacementContext(new ExpectimaxDeplacement(g));

            while (!g.partieFinie()) {

                System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), Bas (s), Monter/Front (r) ou Descendre/Back (f), direction Aléatoire (a)");

                String s = sc.nextLine();
                s.toLowerCase();

                if (!(s.equals("d") || s.equals("droite")
                        || s.equals("q") || s.equals("gauche")
                        || s.equals("z") || s.equals("haut")
                        || s.equals("r") || s.equals("monter")
                        || s.equals("f") || s.equals("descendre")
                        || s.equals("a") || s.equals("aléatoire") || s.equals("aleatoire")
                        || s.equals("s") || s.equals("bas"))) {
                    System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut, s pour Bas, f pour Monter, r pour Descendre ou a pour Aleatoire");
                } else {
                    Direction direction = Direction.RIGHT;  //initialisation
                    if (s.equals("d") || s.equals("droite")) {
                        direction = Direction.RIGHT;
                    } else if (s.equals("z") || s.equals("haut")) {
                        direction = Direction.UP;

                    } else if (s.equals("q") || s.equals("gauche")) {
                        direction = Direction.LEFT;
                    } else if (s.equals("s") || s.equals("bas")) {
                        direction = Direction.DOWN;
                    } else if (s.equals("r") || s.equals("monter")) {
                        direction = Direction.FRONT;
                    } else if (s.equals("f") || s.equals("descendre")) {
                        direction = Direction.BACK;
                    } else {
                        direction = context.executeStrategy();
                    }
                    boolean b2 = g.lanceurDeplacerCases(direction);
                    if (b2) {
                        b = g.nouvelleCase();
                    } else {
                        System.out.println("Non");
                    }
                    System.out.println(g + "\nVous avez fait le déplacement : " + direction + "\n");

                    if (b2) {
                        b = g.nouvelleCase();
                        System.out.println("Score: " + g.getScore());
                    }

                }

            }

        }

    }

}
