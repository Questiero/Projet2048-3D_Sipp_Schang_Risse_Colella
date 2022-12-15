/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet20483D.fxml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static projet20483D.database.Utilisateur.u;

/**
 * FXML Controller class
 *
 * @author risse
 */
public class PageAccueilController implements Initializable {

    @FXML
    private Button boutonJouer, boutonQuitter, boutonClassement, boutonCharger, boutonInscrire, boutonSeConnecter;
    @FXML
    private SplitPane splitPaneJouer;
    @FXML
    private Label labelAffichagePseudoAccueil;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //création fichier sérialisation
            final FileOutputStream fichier = new FileOutputStream("donnees.ser");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PageAccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //style des boutons pour css
        boutonJouer.getStyleClass().add("boutons");
        boutonCharger.getStyleClass().add("boutons");
        boutonClassement.getStyleClass().add("boutons");
        boutonQuitter.getStyleClass().add("boutons");

        labelAffichagePseudoAccueil.setText(u.getPseudo());

    }

    @FXML
    private void cliquerBoutonJouer(MouseEvent event) throws IOException {
        //crétion nouveau fichier sérialisation
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fichier = new FileOutputStream("donnees.ser");
            fichier.flush();

        } catch (final java.io.IOException e) {
            e.printStackTrace();
        }

        //ouverture page setup
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageSetup.fxml"));
        Stage stage = (Stage) boutonJouer.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
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

        System.exit(0);

    }

    @FXML
    private void cliquerBoutonClassement(MouseEvent event) throws IOException {
        //ouverture page classement
        Stage stage;
        Parent root;

        stage = (Stage) boutonClassement.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageClassement.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cliquerBoutonInscrire(MouseEvent event) throws IOException {
//ouverture page inscription
        Stage stage;
        Parent root;

        stage = (Stage) boutonInscrire.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageInscription.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cliquerBoutonSeConnecter(MouseEvent event) throws IOException {
//ouverture page connexion
        Stage stage;
        Parent root;

        stage = (Stage) boutonSeConnecter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageConnexion.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cliquerBoutonCharger(MouseEvent event) throws IOException {
        //ouverture page jeu
        Stage stage;
        Parent root;

        stage = (Stage) boutonSeConnecter.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageJeu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();

    }

}
