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
import static projet20483D.fxml.Utilisateur.u;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageConnectionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    Requete r = new Requete();

    @FXML
    private Button boutonOkConnection;
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

        /* affichage message d'erreur 
        
        if (r.connexion(textFieldPseudoConnection.getText(), textFieldMdpConnection.getText())) {
            stage = (Stage) boutonOkConnection.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("projet20483D/fxml/themeClassique.css");
            stage.setScene(scene);
            stage.show();
        } else {
            labelErreurConnexion.setVisible(true);
        }*/
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));
        stage = (Stage) boutonOkConnection.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/fxml/themeClassique.css");
        stage.setScene(scene);
        stage.show();

        r.connexion(textFieldPseudoConnection.getText(), textFieldMdpConnection.getText());
    }

}
