/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet20483D.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author risse
 */
public class PageAccueilController implements Initializable {

    @FXML
    private Button boutonJouer, boutonQuitter, boutonCredits, boutonCharger;
    @FXML
    private SplitPane splitPaneJouer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boutonJouer.getStyleClass().add("boutons");
        boutonCharger.getStyleClass().add("boutons");
        boutonCredits.getStyleClass().add("boutons");
        boutonQuitter.getStyleClass().add("boutons");
    }    

    @FXML
    private void cliquerBoutonJouer(MouseEvent event) throws IOException {
        //ouverture page setup        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageSetup.fxml"));
        Stage stage = (Stage) boutonJouer.getScene().getWindow();      
        
        Scene scene = new Scene(root); 
        scene.getStylesheets().add("projet20483D/fxml/themeClassique.css");
                
        stage.setScene(scene);
        stage.show();  
    } 

    @FXML
    private void cliquerBoutonQuitter(MouseEvent event) throws IOException {
        //fermeture page
        Stage stage;
        Parent root;

        stage = (Stage) boutonQuitter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.close();
    }

    @FXML
    private void cliquerBoutonCredits(MouseEvent event) throws IOException {
        //fermeture page
        Stage stage;
        Parent root;
        
        stage = (Stage) boutonCredits.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageCredits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void cliquerBoutonInscrire(MouseEvent event) throws IOException {
        //fermeture page
        Stage stage;
        Parent root;
        
        stage = (Stage) boutonCredits.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageCredits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void cliquerBoutonSeConnecter(MouseEvent event) throws IOException {
        //fermeture page
        Stage stage;
        Parent root;
        
        stage = (Stage) boutonCredits.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageCredits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
