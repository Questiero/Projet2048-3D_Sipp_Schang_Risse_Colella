package projet20483D;

import java.io.Serializable;

/**
 * Adaptation de la classe Casse du TP 2048 de L2 en trois dimensions
 */
public class Case implements Parametres, Serializable {

    /**
     * Coordonnées et valeur
     */
    private int x, y, z, valeur;
    /**
     * Grille à laquelle appartient la Case
     */
    private Grille3D grille;

    /**
     * Création d'une case avec ses coordonnées et sa valeur
     *
     * @param abs Abscisse
     * @param ord Ordonnée
     * @param prof Profondeur
     * @param val Valeur
     */
    public Case(int abs, int ord, int prof, int val) {
        this.x = abs;
        this.y = ord;
        this.z = prof;
        this.valeur = val;
    }

    /**
     * Configure la grille à laquelle la Case appartient
     *
     * @param g Grille3D à laquelle la Case appartient
     */
    public void setGrille(Grille3D g) {
        this.grille = g;
    }

    /**
     * Retourne la coordonnée x de la Case
     *
     * @return Coordonnée x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la coordonnée y de la Case
     *
     * @return Coordonnée y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Retourne la coordonnée z de la Case
     *
     * @return Coordonnée z
     */
    public int getZ() {
        return this.z;
    }

    /**
     * Configure la coordonnée x de la Case
     *
     * @param x Coordonnée x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Configure la coordonnée y de la Case
     *
     * @param y Coordonnée y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Configure la coordonnée z de la Case
     *
     * @param z Coordonnée z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Configure la valeur de la Case
     *
     * @param valeur Valeur de la Case
     */
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    /**
     * Retourne la valeur de la Case
     *
     * @return valeur de la Case
     */
    public int getValeur() {
        return this.valeur;
    }

    @Override
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y && this.z == c.z);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13 + this.z * 29;
    }

    /**
     * Compare sa valeur à la valeur d'une autre Case c
     * @param c Case à comparer
     * @return égalité entre la valeur de la Case c et celle-ci
     */
    public boolean valeurEgale(Case c) {
        if (c != null) {
            return this.valeur == c.valeur;
        } else {
            return false;
        }
    }

    /**
     * Retourne la Case voisine le plus proche dans une Direction donnée si il existe, {@code null} sinon.
     * @param direction Direction du voisin
     * @return Case voisine la plus proche
     */
    public Case getVoisinDirect(Direction direction) {
        if (direction == Direction.UP) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i && c.getZ() == this.z) {
                        return c;
                    }
                }
            }
        } else if (direction == Direction.DOWN) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i && c.getZ() == this.z) {
                        return c;
                    }
                }
            }
        } else if (direction == Direction.LEFT) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y && c.getZ() == this.z) {
                        return c;
                    }
                }
            }
        } else if (direction == Direction.RIGHT) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y && c.getZ() == this.z) {
                        return c;
                    }
                }
            }
        } else if (direction == Direction.FRONT) {
            for (int i = this.z - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == this.y && c.getZ() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == Direction.BACK) {
            for (int i = this.z + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == this.y && c.getZ() == i) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.z + "," + this.valeur + ")";
    }

}
