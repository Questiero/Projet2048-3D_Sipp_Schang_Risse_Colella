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
import javafx.scene.input.MouseEvent;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button boutonJouer, boutonQuitter, boutonParametres;

    @FXML
    private void cliquerBoutonJouer(MouseEvent event) throws IOException {
       //ouverture nouvelle page
        Stage stage;
        Parent root;

        //AJOUTER UNE PAGE DE REGLAGES DE LA PARTIE AVANT
        stage = (Stage) boutonJouer.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/pageJeu.fxml"));
        Scene scene = new Scene(root);
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
