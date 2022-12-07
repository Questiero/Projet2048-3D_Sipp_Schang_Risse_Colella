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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet20483D.database.Requete;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageInscriptionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private Button boutonOkInscription;
    
    private Requete requete = new Requete();

    @FXML
    private void cliquerBoutonOkInscription(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) boutonOkInscription.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/fxml/themeClassique.css"); 
        stage.setScene(scene);
        stage.show();
        
        
        requete.inscription("chlonii", "123456789", "123456789");
        
    }

}
