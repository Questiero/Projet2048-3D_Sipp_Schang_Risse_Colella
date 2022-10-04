/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet20483D;

/**
 *
 * @author risse
 */
public class Grille3D implements Parametres {

    private final Grille[] layers = new Grille[3];   //layer 0 = étage du bas (direction back) | layer 1 = milieu | layer 2 = étage du haut (direction front)

    public Grille3D() {
        // A MODIFIER
        for (int i = 0; i < layers.length; i++) {
            this.layers[0].getGrille();
        }

    }

    public Grille[] getLayers() {
        return layers;
    }

}
