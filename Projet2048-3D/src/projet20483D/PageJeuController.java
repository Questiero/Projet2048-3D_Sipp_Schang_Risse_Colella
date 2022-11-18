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
    private Button boutonRetourPageJeu, boutonJouerJeu, boutonUP, boutonDOWN, boutonRIGHT, boutonLEFT, boutonFRONT, boutonBACK, boutonRANDOM, boutonCommentJouer;
    @FXML
    private Label label00_G0, label10_G0, label20_G0, label01_G0, label11_G0, label21_G0, label02_G0, label12_G0, label22_G0;
    @FXML
    private Label label00_G1, label10_G1, label20_G1, label01_G1, label11_G1, label21_G1, label02_G1, label12_G1, label22_G1;
    @FXML
    private Label label00_G2, label10_G2, label20_G2, label01_G2, label11_G2, label21_G2, label02_G2, label12_G2, label22_G2;
    @FXML
    private GridPane grille0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boutonJouerJeu.getStyleClass().add("boutons");
        boutonRetourPageJeu.getStyleClass().add("boutons");
        boutonUP.getStyleClass().add("boutons");
        boutonDOWN.getStyleClass().add("boutons");
        boutonLEFT.getStyleClass().add("boutons");
        boutonRIGHT.getStyleClass().add("boutons");
        boutonFRONT.getStyleClass().add("boutons");
        boutonBACK.getStyleClass().add("boutons");
        boutonRANDOM.getStyleClass().add("boutons");
        boutonCommentJouer.getStyleClass().add("boutons");

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
    private void lancerPartie(MouseEvent event) throws IOException {

        //création du début du jeu (grilles)
        Grille3D g0 = new Grille3D();
        g0.nouvelleCase();
        System.out.println(g0);

        //mise dans un tableau des valeurs des grilles
        int[][][] tab = new int[TAILLE][TAILLE][TAILLE];
        for (Case c : g0.getGrille()) {
            tab[c.getY()][c.getX()][c.getZ()] = c.getValeur();
        }

        //création d'une liste pour les labels de nos grilles
        ArrayList<Label> listeLabels = new ArrayList<>();

        //ajouts des labels dans la liste
        listeLabels.add(label00_G0);
        listeLabels.add(label10_G0);
        listeLabels.add(label20_G0);
        listeLabels.add(label01_G0);
        listeLabels.add(label11_G0);
        listeLabels.add(label21_G0);
        listeLabels.add(label02_G0);
        listeLabels.add(label12_G0);
        listeLabels.add(label22_G0);

        listeLabels.add(label00_G1);
        listeLabels.add(label10_G1);
        listeLabels.add(label20_G1);
        listeLabels.add(label01_G1);
        listeLabels.add(label11_G1);
        listeLabels.add(label21_G1);
        listeLabels.add(label02_G1);
        listeLabels.add(label12_G1);
        listeLabels.add(label22_G1);

        listeLabels.add(label00_G2);
        listeLabels.add(label10_G2);
        listeLabels.add(label20_G2);
        listeLabels.add(label01_G2);
        listeLabels.add(label11_G2);
        listeLabels.add(label21_G2);
        listeLabels.add(label02_G2);
        listeLabels.add(label12_G2);
        listeLabels.add(label22_G2);

        //écriture des valeurs du tableau (=grille3D) dans nos labels
        ArrayList<Label> listeLabelsCopie = new ArrayList<Label>(listeLabels);  //copie de la liste

        for (int k = 0; k < TAILLE; k++) {
            for (int i = 0; i < TAILLE; i++) {
                for (int j = 0; j < TAILLE; j++) {
                    listeLabelsCopie.get(0).setText(Integer.toString(tab[i][j][k]));    //travail dans la copie                    
                    listeLabelsCopie.remove(0);

                }
            }

        }

        boutonJouerJeu.setVisible(false);
        boutonJouerJeu.setDisable(true);

    }

}
