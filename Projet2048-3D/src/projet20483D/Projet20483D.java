package projet20483D;

import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Projet20483D implements Parametres {

    /**
     * @param args the command line arguments
     */
    private static ArrayList savedStates = new ArrayList();

    public static void addMemento(Object m) {      //add de l'objet dans la liste de saves
        savedStates.add(m);
    }

    public static Object getMemento(int index) {   //getter pour avoir notre objet
        return savedStates.get(index);
    }

    public static void main(String[] args) {

        Grille3D g = new Grille3D();
        boolean b = g.nouvelleCase();

        Grille3D originator = new Grille3D();

        Scanner sc = new Scanner(System.in);
        RandomDeplacement directionRandom = new RandomDeplacement();    //appel à la méthode next() 

        while (!g.partieFinie()) {

            System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), Bas (s), Monter/Front (f) ou Descendre/Back (r), direction Aléatoire (a)");

            String s = sc.nextLine();
            s.toLowerCase();

            if (s.equals("u") || s.equals("undo")) {
                originator.restoreFromMemento(getMemento(1));
            }
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
                    direction = directionRandom.next();
                }
                boolean b2 = g.lanceurDeplacerCases(direction);
                if (b2) {
                    b = g.nouvelleCase();

                    originator.set(g);
                    try {
                        addMemento(originator.saveToMemento());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Projet20483D.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Projet20483D.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!b) {
                        g.gameOver();
                    }
                }
                System.out.println(g + "\nVous avez fait le déplacement : " + direction + "\n");
                if (g.getValeurMax() >= OBJECTIF) {
                    g.victory();
                }
            }
        }

        g.gameOver();

    }
}
