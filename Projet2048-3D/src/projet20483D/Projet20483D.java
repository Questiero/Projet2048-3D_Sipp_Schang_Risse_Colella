package projet20483D;

import java.util.Scanner;
import java.util.*;
import projet20483D.Grille3D.Memento;
import projet20483D.strategies.deplacements.Benchmark;
import projet20483D.strategies.deplacements.RandomDeplacement;
import projet20483D.strategies.deplacements.DeplacementContext;

/**
 * Classe principale
 */
public class Projet20483D implements Parametres {

    /**
     * Liste des derniers états de la partie
     */
    public static LinkedList savedStates = new LinkedList();

    /**
     * Ajoute un Memento à la liste des états sauvegardés
     *
     * @param m Memento à ajouter en mémoire
     */
    public static void addMemento(Object m) {
        savedStates.addFirst(m);
        if (savedStates.size() == 6) {
            savedStates.removeLast();
        }
    }

    /**
     * Retourne le dernier Memento sauvegardé en mémoire
     *
     * @return Dernier état en mémoire
     */
    public static Object getMemento() {
        Object nan = savedStates.getFirst();
        savedStates.removeFirst();
        return nan;
    }

    /**
     * Retourne l'état sauvegardé en mémoire dans un Memento
     *
     * @param memento Memento contenant l'état à restaurer
     * @return Etat sauvegardé dans le Memento
     */
    public static Grille3D restoreFromMemento(Object memento) {
        return ((Memento) memento).getSavedState();
    }

    /**
     * Fonction main de l'application. Par défault, se lance avec interface
     * graphique. Si il y a un argument différent de "b", se lance en console.
     * Si l'argument est "b", lance le Benchmark
     *
     * @param args Arguments
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            InterfaceGraphique.main(args);
        } else if (args[0].equals("b")) {
            Benchmark.main(args);
        } else {

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

                System.out.println(g.gameOverMessage());

            }

        }
    }
}
