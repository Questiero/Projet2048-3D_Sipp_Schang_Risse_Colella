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
 * Classe Controlleur FXML de la page de classement
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

        //mise dans un tableau grace à split les valeurs des 5 meilleurs scores et leur pseudo associés
        String clas[] = new String[9];
        String temp = r.getClassement();
        clas = temp.split(";");

        //mise des pseudos du tableau dans l'interface
        labelPseudoRang1.setText(clas[0]);
        labelPseudoRang2.setText(clas[2]);
        labelPseudoRang3.setText(clas[4]);
        labelPseudoRang4.setText(clas[6]);
        labelPseudoRang5.setText(clas[8]);

        //mise des scores du tableau dans l'interface
        labelScoreRang1.setText(clas[1]);
        labelScoreRang2.setText(clas[3]);
        labelScoreRang3.setText(clas[5]);
        labelScoreRang4.setText(clas[7]);
        labelScoreRang5.setText(clas[9]);
    }

    @FXML
    private void cliquerBoutonRetourPageClassement(MouseEvent event) throws IOException {
        //retour à l'accueil
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
