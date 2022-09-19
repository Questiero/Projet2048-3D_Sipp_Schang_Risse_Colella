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
            
            System.out.println("Déplacer vers la Droite (d), Gauche (g), Haut (h), ou Bas (b) ?");
            
            String s = sc.nextLine();
            s.toLowerCase();
            
            if (!(s.equals("d") || s.equals("droite")
                    || s.equals("g") || s.equals("gauche")
                    || s.equals("h") || s.equals("haut")
                    || s.equals("b") || s.equals("bas"))) {
                System.out.println("Vous devez écrire d pour Droite, g pour Gauche, h pour Haut ou b pour Bas");
            } else {
                int direction;
                if (s.equals("d") || s.equals("droite")) {
                    direction = DROITE;
                } else if (s.equals("g") || s.equals("gauche")) {
                    direction = GAUCHE;
                } else if (s.equals("h") || s.equals("haut")) {
                    direction = HAUT;
                } else {
                    direction = BAS;
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

