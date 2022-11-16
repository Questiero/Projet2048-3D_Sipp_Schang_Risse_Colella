/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet20483D;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static projet20483D.Parametres.ETAGES;
import static projet20483D.Parametres.TAILLE;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageJeuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button boutonRetourPageJeu, boutonTest;
    @FXML
    private Label label00, label10, label20, label01, label11, label21, label02, label12, label22;
    @FXML
    private GridPane grille0;
    @FXML
    private Pane pane00_G0, pane10_G0, pane20_G0, pane01_G0, pane11_G0, pane21_G0, pane02_G0, pane12_G0, pane22_G0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cliquerBoutonRetourPageJeu(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageJeu.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/theme.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void testCase(MouseEvent event) throws IOException {

        Grille3D g0 = new Grille3D();
        g0.nouvelleCase();
        System.out.println(g0);

        int[][][] tab = new int[TAILLE][TAILLE][TAILLE];
        for (Case c : g0.getGrille()) {
            tab[c.getY()][c.getX()][c.getZ()] = c.getValeur();
        }
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                for (int k = 0; k < TAILLE; k++) {
                    System.out.println(Integer.toString(tab[i][j][k]));     //le tableau fonctionne
                }

            }
        }

        ArrayList<Pane> listePaneGrille0 = new ArrayList<>();

        listePaneGrille0.add(pane00_G0);

        listePaneGrille0.add(pane00_G0);

        listePaneGrille0.add(pane10_G0);

        listePaneGrille0.add(pane20_G0);

        listePaneGrille0.add(pane01_G0);

        listePaneGrille0.add(pane11_G0);

        listePaneGrille0.add(pane21_G0);

        listePaneGrille0.add(pane02_G0);

        listePaneGrille0.add(pane12_G0);

        listePaneGrille0.add(pane22_G0);

        for (int j = 0; j < TAILLE; j++) {

            int i = 0;
            while (i < 3) {
                for (Pane pane : listePaneGrille0) {
                    for (Node node : pane.getChildren()) {
                        //for (int i = 0; i < TAILLE; i++) {              //i doit s'incrémenter en meme temps que pane et label
                        if (node instanceof Label) {
                            //System.out.println(Integer.toString(tab[i][j][0]));

                            ((Label) node).setText(Integer.toString(tab[i][j][0]));     //pb surement ici dans l'ordre des boubles
                            i++;
                        }
                    }
                }

            }
        }

        /*for (Case b : g0.getGrille()) {
            if (b.getValeur() == 0) {
                System.out.println("0");
            } else {
                System.out.println(b.getValeur());
            }
        }

        /*HashSet<Case> casesG0 = new HashSet<Case>();
        casesG0 = g0.getGrille();
        for (Case a : casesG0){
            System.out.println(a.getValeur());
        }*/
 /*for (Pane pane : listePaneGrille0) {
            for (Node node : pane.getChildren()) {
                if (node instanceof Label) {
                    //((Label) node).setText("A");
                    for (int i = 0; i < 9; i++) {
                        for (Case c : casesG0) {
                            ((Label) node).setText(Integer.toString(c.getValeur()));
                        }
                    }
                }
            }
            for (Node node : grille0.getChildren()){
            
            if (node instanceof Pane){
                ((Label) node).setText("A");
            }
        }*/
    }


}
