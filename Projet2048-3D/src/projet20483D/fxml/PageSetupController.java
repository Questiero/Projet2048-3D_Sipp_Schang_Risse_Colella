package projet20483D.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static projet20483D.database.Utilisateur.u;

/**
 * Classe Controlleur FXML de la page des paramètres
 */
public class PageSetupController implements Initializable {

    @FXML
    private Button boutonLancerPartie, boutonRetourPageSetup;
    @FXML
    private ChoiceBox choiceBoxTheme;
    @FXML
    private Label labelAffichagePseudoSetup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //choice box des themes
        choiceBoxTheme.setItems(FXCollections.observableArrayList("Classique", "Frozen", "Rainbow"));
        choiceBoxTheme.getSelectionModel().select(0);

        //affichage du pseudo de l'utilisateur connecté
        labelAffichagePseudoSetup.setText(u.getPseudo());

    }

    @FXML
    private void cliquerBoutonRetourPageSetup(MouseEvent event) throws IOException {
        //retour à la page d'accueil
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageSetup.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cliquerBoutonLancerPartie(MouseEvent event) throws IOException {
        //ouverture page de jeu
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageJeu.fxml"));
        Stage stage = (Stage) boutonLancerPartie.getScene().getWindow();

        Scene scene = new Scene(root);

        //sélection du css/style choisi par le joueur
        Object selectedItems = choiceBoxTheme.getSelectionModel().getSelectedItem();
        String theme = String.valueOf(selectedItems);

        scene.getStylesheets().add("projet20483D/css/theme" + theme + ".css");

        stage.setScene(scene);
        stage.show();
    }

}
