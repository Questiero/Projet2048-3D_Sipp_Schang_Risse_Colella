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
        //TODO
        return null;
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
        //TODO
        return true;
    }

    public boolean lanceurDeplacerCases(Direction direction) {
        //TODO
        return true;
    }

    private void fusion(Case c) {
        c.setValeur(c.getValeur() * 2);
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        deplacement = true;
    }

    private void deplacerCasesRecursif(Case[] extremites, int rangee, Direction direction, int compteur) {
        //TODO
    }

    public Case[] getCasesExtremites(Direction direction) {
        //TODO
        return null;
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
            System.out.println(ajout);
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
