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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet20483D.database.Requete;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageClassementController implements Initializable {
    
    @FXML
    private Button boutonRetourPageClassement;
    @FXML
    private Label labelPseudoRang1, labelPseudoRang2, labelPseudoRang3, labelPseudoRang4, labelPseudoRang5;
    @FXML
    private Label labelScoreRang1, labelScoreRang2, labelScoreRang3, labelScoreRang4, labelScoreRang5;

    Requete r = new Requete();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String clas[] = new String[9];
        String temp = r.getClassement();        
        clas = temp.split(";");
        
        labelPseudoRang1.setText(clas[0]);
        labelPseudoRang2.setText(clas[2]);
        labelPseudoRang3.setText(clas[4]);
        labelPseudoRang4.setText(clas[6]);
        labelPseudoRang5.setText(clas[8]);
        
        labelScoreRang1.setText(clas[1]);
        labelScoreRang2.setText(clas[3]);
        labelScoreRang3.setText(clas[5]);
        labelScoreRang4.setText(clas[7]);
        labelScoreRang5.setText(clas[9]);        
    }    
    
    @FXML
    private void cliquerBoutonRetourPageClassement(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageClassement.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();

        
    }
    
}
