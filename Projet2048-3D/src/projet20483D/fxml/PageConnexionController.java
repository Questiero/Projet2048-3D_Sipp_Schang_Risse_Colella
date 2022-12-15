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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet20483D.database.Requete;
import static projet20483D.database.Utilisateur.u;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageConnexionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    Requete r = new Requete();

    @FXML
    private Button boutonOkConnection, boutonRetourPageConnexion;
    @FXML
    private TextField textFieldPseudoConnection;
    @FXML
    private PasswordField textFieldMdpConnection;
    @FXML
    private Label labelErreurConnexion;

    @FXML
    private void cliquerBoutonOkConnection(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        // affichage message d'erreur 
        
        boolean b = r.connexion(textFieldPseudoConnection.getText(), textFieldMdpConnection.getText());
        
        if (b) {
            stage = (Stage) boutonOkConnection.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("projet20483D/css/themeClassique.css");
            stage.setScene(scene);
            stage.show();
        } else {
            labelErreurConnexion.setVisible(true);
        }
        
        
    }
    
    @FXML
    private void cliquerBoutonRetourPageConnexion(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageConnexion.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();

        
    }

}
