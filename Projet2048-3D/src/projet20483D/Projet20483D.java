package projet20483D;

import java.util.Scanner;
import java.util.*;
import projet20483D.Grille3D.Memento;
import projet20483D.strategies.deplacements.RandomDeplacement;
import projet20483D.strategies.deplacements.DeplacementContext;

public class Projet20483D implements Parametres {

    public static LinkedList savedStates = new LinkedList();

    public static void addMemento(Object m) {      //add de l'objet dans la liste de saves
        savedStates.addFirst(m);
        if (savedStates.size() == 6) {
            savedStates.removeLast();
        }
    }

    public static Object getMemento() {   //getter pour avoir notre objet
        Object nan = savedStates.getFirst();
        savedStates.removeFirst();
        return nan;
    }

    public static Grille3D restoreFromMemento(Object memento) {
        return ((Memento) memento).getSavedState();
    }

    public static void main(String[] args) {
        Scanner scStart = new Scanner(System.in);

        System.out.println("Comment voulez-vous lancer le jeu ?\nc : Dans la console\ni : Dans l'interface graphique");
        String start = scStart.nextLine();
        start.toLowerCase();

        while (!start.equals("c") && !start.equals("i")) {
            System.out.println("\nEntrez seulement les caractères c ou i.\n\nComment voulez-vous lancer le jeu ?\nc : Dans la console\ni : Dans l'interface graphique");
            start = scStart.nextLine();
            start.toLowerCase();
        }

        if (start.equals("c")) {

            Grille3D g = new Grille3D();
            boolean b = g.nouvelleCase();

            Scanner sc = new Scanner(System.in);

            DeplacementContext context = new DeplacementContext(new RandomDeplacement());
            // DeplacementContext context = new DeplacementContext(new PMCTSDeplacement(g, 10));
            // DeplacementContext context = new DeplacementContext(new ExpectimaxDeplacement(g));

            while (!g.partieFinie()) {

                System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), Bas (s), Monter/Front (f) ou Descendre/Back (r), direction Aléatoire (a)");

                String s = sc.nextLine();
                s.toLowerCase();

                if (s.equals("u") || s.equals("undo")) {
                    if (savedStates.size() > 0) {
                        g = restoreFromMemento(getMemento());
                    } else {
                        System.out.println("Nan");
                    }
                }
                if (!(s.equals("d") || s.equals("droite")
                        || s.equals("q") || s.equals("gauche")
                        || s.equals("z") || s.equals("haut")
                        || s.equals("r") || s.equals("monter")
                        || s.equals("f") || s.equals("descendre")
                        || s.equals("a") || s.equals("aléatoire") || s.equals("aleatoire")
                        || s.equals("s") || s.equals("bas"))) {
                    System.out.println("Vous devez écrire d pour Droite, q pour Gauche, z pour Haut, s pour Bas, r pour Monter, f pour Descendre ou a pour Aleatoire");
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

                    addMemento(new Memento(g));

                    boolean b2 = g.lanceurDeplacerCases(direction);

                    if (b2) {

                        b = g.nouvelleCase();

                    } else {
                        System.out.println("Non");
                        savedStates.removeFirst();
                    }
                    System.out.println(g + "\nVous avez fait le déplacement : " + direction + "\n");
                    System.out.println("Score: " + g.getScore());

                }

            }

            System.out.println(g.gameOverMessage());

        } else if (start.equals("i")) {             //lancement dans interface graphique
            InterfaceGraphique.main(args);
        }
    }

}
