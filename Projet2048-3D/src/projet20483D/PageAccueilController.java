/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet20483D;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author risse
 */
public class PageAccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button boutonJouer, boutonQuitter, boutonParametres, boutonCharger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boutonJouer.getStyleClass().add("boutons");
        boutonCharger.getStyleClass().add("boutons");
        boutonParametres.getStyleClass().add("boutons");
        boutonQuitter.getStyleClass().add("boutons");

    }

    @FXML
    private void cliquerBoutonJouer(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/pageJeu.fxml"));
        Stage stage = (Stage) boutonJouer.getScene().getWindow();
        
        

        //AJOUTER UNE PAGE DE REGLAGES DE LA PARTIE AVANT
        Scene scene = new Scene(root); 
        scene.getStylesheets().add("projet20483D/theme.css");
        
        
        stage.setScene(scene);
        stage.show();
        
        

    }

    @FXML
    private void cliquerBoutonQuitter(MouseEvent event) throws IOException {
        //fermeture page
        Stage stage;
        Parent root;

        stage = (Stage) boutonQuitter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/pageAccueil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.close();
    }

    @FXML
    private void cliquerBoutonParametres(MouseEvent event) throws IOException {
        //fermeture page
        Stage stage;
        Parent root;

        //A MODIF EN CREANT NOUVELLE PAGES POUR PARAMETTRE
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        stage = (Stage) boutonParametres.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/pageAccueil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
