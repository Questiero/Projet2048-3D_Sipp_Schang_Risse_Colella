package projet20483D.strategies.deplacements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet20483D.Case;
import projet20483D.Direction;
import projet20483D.Grille3D;
import projet20483D.Parametres;

/**
 * Stratégie de déplacement basée sur le modèle d'Expectimax
 */
public class ExpectimaxDeplacement implements DeplacementStrategy, Parametres {

    private Grille3D grille;
    private final int depth;
    private final ExpectimaxType type;

    /**
     * Crée une stratégie de déplacement basée sur le modèle d'Expectimax sur
     * une Grille3D avec une profondeur maximum de 2 et un heuristique naif
     *
     * @param grille Grille3D
     */
    public ExpectimaxDeplacement(Grille3D grille) {

        this.grille = grille;
        this.depth = 2;
        this.type = ExpectimaxType.NAIVE;

    }

    /**
     * Crée une stratégie de déplacement basée sur le modèle d'Expectimax sur
     * une Grille3D avec une profondeur maximum configurable et un heuristique
     * naif
     *
     * @param grille Grille3D
     * @param depth Profondeur maximum
     */
    public ExpectimaxDeplacement(Grille3D grille, int depth) {

        this.grille = grille;
        this.depth = depth;

        this.type = ExpectimaxType.NAIVE;

    }

    /**
     * Crée une stratégie de déplacement basée sur le modèle d'Expectimax sur
     * une Grille3D avec une profondeur maximum configurable et un heuristique
     * choisi entre NAIVE, EMPTYONLY et ADVANCED
     *
     * @param grille Grille3D
     * @param depth Profondeur maximum
     * @param type Type d'heuristique
     */
    public ExpectimaxDeplacement(Grille3D grille, int depth, ExpectimaxType type) {

        this.grille = grille;
        this.depth = depth;
        this.type = type;

    }

    @Override
    public Direction next() {

        MaxNode root = new MaxNode(this.grille, 0);

        return root.getBestDirection();

    }

    /**
     * Classe représentant un noeud de l'arbre de décision
     */
    private abstract class Node implements Parametres {

        /**
         * Profondeur du noeud
         */
        protected int depth;

        /**
         * Grille3D associée à l'état actuel du noeud
         */
        protected Grille3D grille;
        /**
         * List des fils du noeud, c'est-à-dire les états atteignables à partir
         * de celui-ci
         */
        protected ArrayList<Node> children = new ArrayList<Node>();

        /**
         * Génération de {@code children}
         */
        protected abstract void generateChildren();

        /**
         * Evaluation de la valeur du noeud récursivement en fonction de ces
         * fils
         *
         * @return Evaluation récursive du noeud
         */
        protected abstract double evaluateRecursive();

        /**
         * Evaluation de la valeur d'un noeud en fonction de l'heuristique
         * définie à la création de la stratégie
         *
         * @return Evaluation du noeud
         */
        protected double selfEvaluate() {

            double poidsVide, poidsMax, poidsMonotony, poidsSmoothness;

            switch (ExpectimaxDeplacement.this.type) {

                case NAIVE:
                    return this.grille.getScore();
                case EMPTYONLY:

                    poidsVide = 2.7;
                    poidsMax = 1;

                    return poidsVide * Math.log(this.grille.getNumberEmpty())
                            + poidsMax * this.grille.getValeurMax();

                case ADVANCED:

                    poidsVide = 2.7;
                    poidsMax = 1;
                    poidsMonotony = 1.;
                    poidsSmoothness = 0.1;

                    return poidsVide * Math.log(this.grille.getNumberEmpty())
                            + poidsMax * this.grille.getValeurMax()
                            + poidsMonotony * this.grille.getMonotony()
                            + poidsSmoothness * this.grille.getSmoothness();

            }

            return 0;

        }

    }

    /**
     * Classe représentant un noeud maximum dans l'arbre de décision
     */
    private final class MaxNode extends Node {

        /**
         * Création d'un noeud maximum
         *
         * @param grille Etat représenté par le noeud
         * @param depth Profondeur actuelle du noeud
         */
        public MaxNode(Grille3D grille, int depth) {

            this.grille = grille;
            this.depth = depth;

            this.generateChildren();

        }

        @Override
        protected void generateChildren() {

            // Vérification que la profondeur maximale ne soit pas atteinte
            if (this.depth >= ExpectimaxDeplacement.this.depth) {
                this.children = null;
            } else {

                for (Direction dir : Direction.values()) {

                    // Génération de tout les children possibles en fonction de toutes les diréctions
                    try {

                        Grille3D newGrille = this.grille.deepCopy();

                        if (newGrille.lanceurDeplacerCases(dir)) {
                            this.children.add(new ChanceNode(newGrille, this.depth + 1, dir));
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(ExpectimaxDeplacement.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ExpectimaxDeplacement.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

        @Override
        protected double evaluateRecursive() {

            double evaluation = 0;

            if (this.children == null || this.children.isEmpty()) {

                evaluation = this.selfEvaluate();

            } else {

                // Get the max of all children
                evaluation = this.children.get(0).evaluateRecursive();

                for (int i = 1; i < this.children.size(); i++) {

                    double tempEvaluation = this.children.get(i).evaluateRecursive();
                    if (tempEvaluation > evaluation) {
                        evaluation = tempEvaluation;
                    } else if (tempEvaluation == evaluation) {
                        Random ra = new Random();
                        if (ra.nextInt(2) == 0) {
                            evaluation = tempEvaluation;
                        }
                    }

                }

            }

            return evaluation;

        }

        /**
         * Retourne la meilleure direction à choisir, basée sur l'évaluation
         * récursive des fils du noeud
         *
         * @return Meilleure direction à choisir
         */
        public Direction getBestDirection() {

            double evaluation = 0;
            Direction bestDirection = null;

            if (this.children == null || this.children.isEmpty()) {

                System.out.println("Cheh");

            } else {

                // Get the max of all children
                evaluation = this.children.get(0).evaluateRecursive();
                bestDirection = ((ChanceNode) this.children.get(0)).getDirection();

                for (int i = 1; i < this.children.size(); i++) {

                    double tempEvaluation = this.children.get(i).evaluateRecursive();
                    if (tempEvaluation > evaluation) {
                        evaluation = tempEvaluation;
                        bestDirection = ((ChanceNode) this.children.get(i)).getDirection();
                    } else if (tempEvaluation == evaluation) {
                        Random ra = new Random();
                        if (ra.nextInt(2) == 0) {
                            evaluation = tempEvaluation;
                            bestDirection = ((ChanceNode) this.children.get(i)).getDirection();
                        }
                    }

                }

            }

            return bestDirection;

        }

    }

    /**
     * Classe représentant un noeud aléatoire dans l'arbre de décision
     */
    private final class ChanceNode extends Node {

        /**
         * Direction associée au noeud
         */
        private Direction direction;

        /**
         * Création d'un noeud aléatoire
         *
         * @param grille Etat représenté par le noeud
         * @param depth Profondeur actuelle du noeud
         * @param direction Direction associée au noeud
         */
        public ChanceNode(Grille3D grille, int depth, Direction direction) {

            this.grille = grille;
            this.depth = depth;

            this.direction = direction;

            this.generateChildren();

        }

        /**
         * Retourne la direction associée au noeud
         *
         * @return Direction associée au noeud
         */
        public Direction getDirection() {
            return this.direction;
        }

        @Override
        protected void generateChildren() {

            // Vérification que la profondeur maximale ne soit pas atteinte
            if (this.depth >= ExpectimaxDeplacement.this.depth) {
                this.children = null;
            } else {

                //Génération de tout les children possibles en fonction de toutes les possibilités aléatoires pour les nouvelles cases
                for (int valeur = 2; valeur <= 4; valeur += 2) {
                    for (int x = 0; x < TAILLE; x++) {
                        for (int y = 0; y < TAILLE; y++) {
                            for (int z = 0; z < TAILLE; z++) {

                                Case c = new Case(x, y, z, valeur);

                                if (!this.grille.getGrille().contains(c)) { // contains utilise la méthode equals dans Case

                                    try {

                                        Grille3D newGrille = this.grille.deepCopy();

                                        newGrille.addCase(c);

                                        this.children.add(new MaxNode(newGrille, this.depth + 1));

                                    } catch (IOException ex) {
                                        Logger.getLogger(ExpectimaxDeplacement.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(ExpectimaxDeplacement.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        @Override
        protected double evaluateRecursive() {

            double evaluation = 0;

            if (this.children == null || this.children.isEmpty()) {

                evaluation = this.selfEvaluate();

            } else {

                // Get the average of all children
                for (Node child : this.children) {
                    evaluation += child.evaluateRecursive();
                }

                evaluation /= this.children.size();

            }

            return evaluation;

        }

    }

    /**
     * Enumération représentant les trois types d'heuristiques disponibles
     */
    public enum ExpectimaxType {
        /**
         * Prends en compte le score
         */
        NAIVE,
        /**
         * Prends en compte la valeur maximale de la grille et son nombre de
         * cases vides
         */
        EMPTYONLY,
        /**
         * Prends en compte la valeur maximale de la grille, son nombre de cases
         * vides, sa monotonie et sa régularité
         */
        ADVANCED
    }

}
