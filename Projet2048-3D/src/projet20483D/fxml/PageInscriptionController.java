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
 * Classe Controlleur FXML de la page d'inscription
 */
public class PageInscriptionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private Button boutonOkInscription, boutonRetourPageInscription;
    @FXML
    private PasswordField textFieldMdpInscription, textFieldMdpConfirmation;
    @FXML
    private TextField textFieldPseudoInscription;
    @FXML
    private Label labelErreurInscription;

    private Requete requete = new Requete();

    @FXML
    private void cliquerBoutonOkInscription(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        String erreur = requete.inscription(textFieldPseudoInscription.getText(), textFieldMdpInscription.getText(), textFieldMdpConfirmation.getText());

        if (erreur.equals("")) {
            //ouverture page accueil
            stage = (Stage) boutonOkInscription.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("projet20483D/css/themeClassique.css");
            stage.setScene(scene);
            stage.show();
            requete.connexion(textFieldPseudoInscription.getText(), textFieldMdpInscription.getText());
        } else {
            //affichage message d'erreur si probl??me d'inscription
            labelErreurInscription.setText(erreur);

        }

    }

    @FXML
    private void cliquerBoutonRetourPageInscription(MouseEvent event) throws IOException {
        //retour ?? la page d'accueil
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageInscription.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();
    }

}
