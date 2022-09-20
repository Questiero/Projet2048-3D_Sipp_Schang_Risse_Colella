package projet20483D;

import java.util.Scanner;

public class Projet20483D implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Grille g = new Grille();
        boolean b = g.nouvelleCase();

        Scanner sc = new Scanner(System.in);

        while (!g.partieFinie()) {

            System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?");

            String s = sc.nextLine();
            s.toLowerCase();

            if (!(s.equals("d") || s.equals("droite")
                    || s.equals("q") || s.equals("gauche")
                    || s.equals("z") || s.equals("haut")
                    || s.equals("s") || s.equals("bas"))) {
                System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut ou s pour Bas");
            } else {
                Direction direction;
                if (s.equals("d") || s.equals("droite")) {
                    direction = Direction.RIGHT;
                } else if (s.equals("z") || s.equals("haut")) {
                    direction = Direction.UP;

                } else if (s.equals("q") || s.equals("gauche")) {
                    direction = Direction.LEFT;
                } else {
                    direction = Direction.DOWN;
                }
                boolean b2 = g.lanceurDeplacerCases(direction);
                if (b2) {
                    b = g.nouvelleCase();
                    if (!b) {
                        g.gameOver();
                    }
                }
                System.out.println(g);
                if (g.getValeurMax() >= OBJECTIF) {
                    g.victory();
                }
            }
        }

        g.gameOver();

    }
}
