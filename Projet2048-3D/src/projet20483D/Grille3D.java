package projet20483D;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Adaptation de la classe Grille du TP 2048 de L2 en trois dimensions
 */
public class Grille3D implements Serializable, Parametres {

    /**
     * Grille contenant les Cases
     */
    private final HashSet<Case> grille;
    /**
     * Valeur maximale atteinte dans la grille
     */
    private int valeurMax = 0;
    /**
     * Score de la partie
     */
    private int score = 0;
    /**
     * Nombres de coups effectués dans la partie
     */
    private int nbCoups = 0;
    /**
     * Variable permettant de savoir si un déplacement est possible ou non
     */
    private boolean deplacement;
    /**
     * Etat de la fin de partie, permet de savoir si c'est une victoire ou non
     */
    private boolean victory = false;

    /**
     * Construit une Grille3D
     */
    public Grille3D() {
        this.grille = new HashSet<Case>();
    }

    @Override
    public String toString() {

        int[][][] tableau = new int[TAILLE][TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()][c.getZ()] = c.getValeur();
        }
        String result = "";

        for (int i = 0; i < TAILLE; i++) {
            if (i != 0) {
                result += "\n";

            }
            for (int k = 0; k < TAILLE; k++) {
                result += "[";
                for (int j = 0; j < TAILLE - 1; j++) {
                    result += String.format("%4d,", tableau[i][j][k]);

                }
                if (k == TAILLE - 1) {
                    result += String.format("%4d]", tableau[i][tableau.length - 1][k]);
                } else {
                    result += String.format("%4d]\t\t", tableau[i][tableau.length - 1][k]);

                }

            }
        }

        return result;
    }

    /**
     * Retourne un HashSet de case représentant la grille
     *
     * @return La grille
     */
    public HashSet<Case> getGrille() {
        return grille;
    }

    /**
     * Retourne la valeur de la Case maximale contenue au sein de la grille
     *
     * @return valeur de la Case maximale contenue au sein de la grille
     */
    public int getValeurMax() {
        return valeurMax;
    }

    /**
     * Retourne le score de la partie, augmentant à chaque fusion de la valeur
     * du nombre fusionné
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Retourne le nombre de coups joués dans la partie
     *
     * @return nombre de coups
     */
    public int getNbCoups() {
        return nbCoups;
    }

    /**
     * Retourne l'état final de la partie: {@code true} si c'est une victoire,
     * {@code false} sinon
     *
     * @return Etat final de la partie
     */
    public boolean isVictory() {
        return victory;
    }

    /**
     * Retourne l'état actuel de la partie: {@code true} si elle est finie,
     * {@code false} false sinon
     *
     * @return Etat actuel de la partie
     */
    public boolean partieFinie() {

        if (this.valeurMax >= OBJECTIF) {
            victory = true;
            return true;
        } else if (this.grille.size() < TAILLE * TAILLE * TAILLE) {
            return false;
        } else {
            for (Case c : this.grille) {
                Direction[] directions = Direction.values();
                for (int i = 0; i < directions.length; i++) {
                    if (c.getVoisinDirect(directions[i]) != null) {
                        if (c.valeurEgale(c.getVoisinDirect(directions[i]))) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;

    }

    /**
     * Essaie de déplacer toutes les cases dans une direction donnée: si au
     * moins un déplacement est réalisé, retourne {@code true} et ajoute une
     * nouvelle case aléatoirement. Sinon, retourne {@code false}.
     *
     * @param direction La direction dans laquelle on souhaite essayer de
     * déplacer les cases
     * @return Etat du déplacement
     */
    public boolean lanceurDeplacerCases(Direction direction) {
        Case[][] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            int secondD = TAILLE;
            for (int j = 0; j < secondD; j++) {
                this.deplacerCasesRecursif(extremites, i, j, direction, 0);
            }
        }
        if (deplacement) {
            nbCoups++;
        }

        return deplacement;
    }

    /**
     * Augmente la valeur d'une Case c, augmente le score et change la code
     * valeurMax si nécessaire.
     *
     * @param c La case fusionnée
     */
    private void fusion(Case c) {
        c.setValeur(c.getValeur() * 2);
        this.score += c.getValeur();
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        deplacement = true;
        //System.out.println("Score: " + this.score);

    }

    /**
     * Déplacement récursif des cases de la grille
     *
     * @param extremites Cases à l'extrémités de la grille dans la Direction
     * souhaitée
     * @param firstD Colonne actuelle
     * @param secondD Ligne actuelle
     * @param direction Direction dans laquelle on souhaite déplacer les cases
     * @param compteur Un compteur
     */
    private void deplacerCasesRecursif(Case[][] extremites, int firstD, int secondD, Direction direction, int compteur) {
        if (extremites[firstD][secondD] != null) {
            if ((direction == Direction.UP && extremites[firstD][secondD].getY() != compteur)
                    || (direction == Direction.DOWN && extremites[firstD][secondD].getY() != TAILLE - 1 - compteur)
                    || (direction == Direction.LEFT && extremites[firstD][secondD].getX() != compteur)
                    || (direction == Direction.RIGHT && extremites[firstD][secondD].getX() != TAILLE - 1 - compteur)
                    || (direction == Direction.FRONT && extremites[firstD][secondD].getZ() != compteur)
                    || (direction == Direction.BACK && extremites[firstD][secondD].getZ() != TAILLE - 1 - compteur)) {

                this.grille.remove(extremites[firstD][secondD]);
                switch (direction) {
                    case UP:
                        extremites[firstD][secondD].setY(compteur);
                        break;
                    case DOWN:
                        extremites[firstD][secondD].setY(TAILLE - 1 - compteur);
                        break;
                    case LEFT:
                        extremites[firstD][secondD].setX(compteur);
                        break;
                    case RIGHT:
                        extremites[firstD][secondD].setX(TAILLE - 1 - compteur);
                        break;
                    case FRONT:
                        extremites[firstD][secondD].setZ(compteur);
                        break;
                    case BACK:
                        extremites[firstD][secondD].setZ(TAILLE - 1 - compteur);
                        break;
                }
                this.grille.add(extremites[firstD][secondD]);
                deplacement = true;
            }
            Case voisin = extremites[firstD][secondD].getVoisinDirect(direction.opposite());
            if (voisin != null) {
                if (extremites[firstD][secondD].valeurEgale(voisin)) {
                    this.fusion(extremites[firstD][secondD]);
                    extremites[firstD][secondD] = voisin.getVoisinDirect(direction.opposite());
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, firstD, secondD, direction, compteur + 1);
                } else {
                    extremites[firstD][secondD] = voisin;
                    this.deplacerCasesRecursif(extremites, firstD, secondD, direction, compteur + 1);
                }
            }
        }
    }

    /**
     * Retourne les cases à l'extrémités dans une direction donnée
     *
     * @param direction Direction des cases
     * @return Tableau à deux dimensions des cases à l'extrémités
     */
    public Case[][] getCasesExtremites(Direction direction) {

        Case[][] result = new Case[TAILLE][TAILLE];

        for (Case c : this.grille) {
            switch (direction) {
                case UP:
                    if ((result[c.getX()][c.getZ()] == null) || (result[c.getX()][c.getZ()].getY() > c.getY())) { // si on n'avait pas encore de case pour cette rangée ou si on a trouvé un meilleur candidat
                        result[c.getX()][c.getZ()] = c;
                    }
                    break;
                case DOWN:
                    if ((result[c.getX()][c.getZ()] == null) || (result[c.getX()][c.getZ()].getY() < c.getY())) {
                        result[c.getX()][c.getZ()] = c;
                    }
                    break;
                case LEFT:
                    if ((result[c.getY()][c.getZ()] == null) || (result[c.getY()][c.getZ()].getX() > c.getX())) {
                        result[c.getY()][c.getZ()] = c;
                    }
                    break;
                case RIGHT:
                    if ((result[c.getY()][c.getZ()] == null) || (result[c.getY()][c.getZ()].getX() < c.getX())) {
                        result[c.getY()][c.getZ()] = c;
                    }
                    break;
                case FRONT:
                    if ((result[c.getX()][c.getY()] == null) || (result[c.getX()][c.getY()].getZ() > c.getZ())) {
                        result[c.getX()][c.getY()] = c;
                    }
                    break;
                case BACK:
                    if ((result[c.getX()][c.getY()] == null) || (result[c.getX()][c.getY()].getZ() < c.getZ())) {
                        result[c.getX()][c.getY()] = c;
                    }
                    break;
            }
        }
        return result;
    }

    /**
     * Retourne un message de fin de partie personnalisé en fonction de l'état
     * de la partie
     *
     * @return Message de fin de partie
     */
    public String gameOverMessage() {
        if (this.victory) {
            return "Bravo ! Vous avez atteint " + this.valeurMax + " avec un score de " + this.score;
        } else {
            return "La partie est finie. Vous avez atteint " + this.valeurMax + " avec un score de " + this.score;
        }
    }

    /**
     * Crée une nouvelle Case aléatoirement et l'ajoute dans la grille Si c'est
     * possible, retourne {@code true}. Sinon, retourne {@code false}.
     *
     * @return Etat du déplacement
     */
    public boolean nouvelleCase() {

        if (this.grille.size() < TAILLE * TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int valeur = (1 + ra.nextInt(2)) * 2;
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    for (int z = 0; z < TAILLE; z++) {
                        Case c = new Case(x, y, z, valeur);
                        if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case
                            casesLibres.add(c);
                        }

                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            this.addCase(ajout);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Ajoute une Case dans la grille
     *
     * @param ajout Case à ajouter
     */
    public void addCase(Case ajout) {
        ajout.setGrille(this);
        this.grille.add(ajout);
        if ((this.grille.size() == 1) || (this.valeurMax == 2 && ajout.getValeur() == 4)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 4 et que l'ancien max était 2
            this.valeurMax = ajout.getValeur();
        }
    }

    /**
     * Création d'une copie profonde de l'instance de Grille3D
     *
     * @return Copie profonde de Grille3D
     * @throws IOException Si il y a une erreur
     * @throws ClassNotFoundException Si il y a une erreur
     */
    public Grille3D deepCopy() throws IOException, ClassNotFoundException {

        //Serialization of object
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        //De-serialization of object
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Grille3D copy = (Grille3D) ois.readObject();

        return copy;

    }

    /**
     * Retourne le nombre de cases vides dans la grille
     *
     * @return Le nombre de cases vides
     */
    public int getNumberEmpty() {
        return TAILLE * TAILLE * TAILLE - this.grille.size();
    }

    /**
     * Retourne la monotonie de la grille, c'est-à-dire une heuristique
     * indiquant si la grille est composée principalement de cases similaires
     * côte à côte
     *
     * @return Monotonie de la grille
     */
    public int getMonotony() {

        int monotony = 0;

        for (Case c1 : this.grille) {

            Direction[] directions = Direction.values();

            for (Direction dir : directions) {

                Case c2 = c1.getVoisinDirect(dir);
                if (c2 != null) {
                    monotony -= Math.log(c1.getValeur()) / Math.log(2) - Math.log(c2.getValeur()) / Math.log(2);
                }

            }

        }

        return monotony;

    }

    /**
     * Retourne la régularité de la grille, c'est-à-dire une heuristique
     * indiquant si la grille est composée principalement de cases de valeur
     * proches côte à côte
     *
     * @return Régularité de la grille
     */
    public int getSmoothness() {

        int smoothness = 0;

        for (Case c1 : this.grille) {

            Direction[] directions = Direction.values();

            for (Direction dir : directions) {

                Case c2 = c1.getVoisinDirect(dir);
                if (c2 != null) {

                    smoothness -= Math.abs(Math.log(c1.getValeur()) / Math.log(2) - Math.log(c2.getValeur()) / Math.log(2));

                }

            }

        }

        return smoothness;

    }

    /**
     * Enregistre l'état actuel d'une grille dans un nouveau Memento
     *
     * @return Memento
     * @throws ClassNotFoundException Si erreur
     * @throws IOException Si erreur
     */
    public Object saveToMemento() throws ClassNotFoundException, IOException {
        System.out.println("Originator: sauvegarde dans le memento.");
        return new Memento(this);
    }

    /**
     * Classe permettant la sauvegarde d'une Grille3D en mémoire via un Memento
     */
    public static class Memento {

        private Grille3D state;

        /**
         * Création d'un nouveau Memento à partir de la Grille3D à sauvegarder
         *
         * @param stateToSave Grille3D à sauvegarder
         */
        public Memento(Grille3D stateToSave) {
            try {
                state = stateToSave.deepCopy();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Grille3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Grille3D.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        /**
         * Récupère la Grille3D sauvegardée
         *
         * @return Grille3D sauvegardée
         */
        public Grille3D getSavedState() {
            return state;
        }

    }

    /**
     * Sérialise la grille dans le fichier donnees.ser
     */
    public void serialisation() {

        ObjectOutputStream oos = null;
        try {
            FileOutputStream fichier = new FileOutputStream("donnees.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
