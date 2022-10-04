package projet20483D;

public class Case implements Parametres {

    private int x, y, z, valeur;
    private Grille3D grille;

    public Case(int abs, int ord, int v, int prof) {
        this.x = abs;
        this.y = ord;
        this.z = prof;
        this.valeur = v;
    }

    public void setGrille(Grille3D g) {
        this.grille = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
    

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

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

    public boolean valeurEgale(Case c) {
        if (c != null) {
            return this.valeur == c.valeur;
        } else {
            return false;
        }
    }

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
            for (int i = this.z + 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == this.y && c.getZ() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == Direction.BACK) {
            for (int i = this.z + 1; i < ETAGES; i++) {
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
