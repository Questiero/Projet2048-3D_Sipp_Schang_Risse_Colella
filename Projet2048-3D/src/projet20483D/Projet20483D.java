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
            
            

            System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), Bas (s), Monter (f) ou Descendre (r)?\nDirection Aléatoire (a)");

            String s = sc.nextLine();
            s.toLowerCase();

            if (!(s.equals("d") || s.equals("droite")
                    || s.equals("q") || s.equals("gauche")
                    || s.equals("z") || s.equals("haut")
                    || s.equals("f") || s.equals("monter")
                    || s.equals("r") || s.equals("descendre")
                    || s.equals("a") || s.equals("aléatoire") || s.equals("aleatoire")
                    || s.equals("s") || s.equals("bas"))) {
                System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut, s pour Bas, f pour Monter, r pour Descendre ou a pour Aleatoire");
            } else {
                Direction direction = Direction.RIGHT;
                if (s.equals("d") || s.equals("droite")) {
                    direction = Direction.RIGHT;
                } else if (s.equals("z") || s.equals("haut")) {
                    direction = Direction.UP;

                } else if (s.equals("q") || s.equals("gauche")) {
                    direction = Direction.LEFT;
                } else if (s.equals("s") || s.equals("bas")){
                    direction = Direction.DOWN;
                } else if (s.equals("f") || s.equals("monter")){
                    direction = Direction.FRONT;
                } else if (s.equals("r") || s.equals("descendre")){
                    direction = Direction.BACK;
                } else {
                    direction = direction.dirRandom(); 
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
