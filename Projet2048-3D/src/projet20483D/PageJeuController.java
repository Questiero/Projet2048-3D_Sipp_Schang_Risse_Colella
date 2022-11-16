/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet20483D;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

        for (Pane pane : listePaneGrille0) {
            for (Node node : pane.getChildren()) {
                if (node instanceof Label) {
                    ((Label) node).setText("A");
                }
            }
            /*for (Node node : grille0.getChildren()){
            
            if (node instanceof Pane){
                ((Label) node).setText("A");
            }
        }*/

        }

    }
}
