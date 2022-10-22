package projet20483D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import static projet20483D.Parametres.TAILLE;

public class Grille3D implements Parametres {

    private final HashSet<Case> grille;
    private int valeurMax = 0;
    private boolean deplacement;

    public Grille3D() {
        this.grille = new HashSet<Case>();
    }

    @Override
    public String toString() {

        int[][][] tableau = new int[TAILLE][TAILLE][ETAGES];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()][c.getZ()] = c.getValeur();
        }
        String result = "";

        for (int i = 0; i < TAILLE; i++) {
            if (i != 0) {
                result += "\n";

            }
            for (int k = 0; k < ETAGES; k++) {
                result += "[";
                for (int j = 0; j < TAILLE - 1; j++) {
                    result += String.format("%4d,", tableau[i][j][k]);

                }
                if (k == ETAGES - 1) {
                    result += String.format("%4d]", tableau[i][tableau.length - 1][k]);
                } else {
                    result += String.format("%4d]\t\t", tableau[i][tableau.length - 1][k]);

                }

            }
        }

        return result;
    }

    public String toHTML() {
        //TODO
        return null;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public boolean partieFinie() {
        // Pas sûr sûr que ça marche, si il y a des bugs c'est sûrement ici
        if (this.grille.size() < TAILLE * TAILLE * ETAGES) {
            return false;
        } else {
            for (Case c : this.grille) {
                Direction[] directions = {Direction.UP, Direction.RIGHT};
                for (int i = 1; i <= 2; i++) {
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

    public boolean lanceurDeplacerCases(Direction direction) {
        Case[][] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            this.deplacerCasesRecursif(extremites, i, direction, 0);
        }
        return deplacement;
    }

    private void fusion(Case c) {
        c.setValeur(c.getValeur() * 2);
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        deplacement = true;
    }

    private void deplacerCasesRecursif(Case[][] extremites, int rangee, Direction direction, int compteur) {
        //TODO
    }

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

    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valeurMax);
        System.exit(0);
    }

    public void gameOver() {
        System.out.println("La partie est finie. Votre score est " + this.valeurMax);
        System.exit(1);
    }

    public boolean nouvelleCase() {

        if (this.grille.size() < TAILLE * TAILLE * ETAGES) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int valeur = (1 + ra.nextInt(2)) * 2;
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    for (int z = 0; z < ETAGES; z++) {
                        Case c = new Case(x, y, z, valeur);
                        if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case
                            casesLibres.add(c);
                        }

                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 2 && ajout.getValeur() == 4)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 4 et que l'ancien max était 2
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }

    }

}
