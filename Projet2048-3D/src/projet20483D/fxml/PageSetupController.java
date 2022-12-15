/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import static projet20483D.fxml.Utilisateur.u;

/**
 * FXML Controller class
 *
 * @author Amandine S
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
        choiceBoxTheme.setItems(FXCollections.observableArrayList("Classique", "Frozen"));
        choiceBoxTheme.getSelectionModel().select(0);
        
        labelAffichagePseudoSetup.setText(u.getPseudo());

    }
    
    @FXML
    private void cliquerBoutonRetourPageSetup(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageSetup.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/fxml/themeClassique.css");
        stage.setScene(scene);
        stage.show();

        
    }

    @FXML
    private void cliquerBoutonLancerPartie(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageJeu.fxml"));
        Stage stage = (Stage) boutonLancerPartie.getScene().getWindow();

        Scene scene = new Scene(root);

        Object selectedItems = choiceBoxTheme.getSelectionModel().getSelectedItem();
        String theme = String.valueOf(selectedItems);

        scene.getStylesheets().add("projet20483D/fxml/theme" + theme + ".css");

        stage.setScene(scene);
        stage.show();
    }

}
