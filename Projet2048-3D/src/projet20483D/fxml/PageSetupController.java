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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageSetupController implements Initializable {

    @FXML
    private Button boutonLancerPartie;
    @FXML
    private ChoiceBox choiceBoxTheme;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBoxTheme.setItems(FXCollections.observableArrayList("Classique", "Frozen"));
    }

    //selec box pour choix theme
    //pseudo
    //si tout ca bon, able bouton lancer
    @FXML
    private void cliquerBoutonLancerPartie(MouseEvent event) throws IOException {
        //ouverture nouvelle page

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageJeu.fxml"));
        Stage stage = (Stage) boutonLancerPartie.getScene().getWindow();

        Scene scene = new Scene(root);
        
        
        Object selectedItems = choiceBoxTheme.getSelectionModel().getSelectedItem();
        String theme = String.valueOf(selectedItems);
      
        scene.getStylesheets().add("projet20483D/fxml/theme"+theme+".css");

        stage.setScene(scene);
        stage.show();
    }

    
}
