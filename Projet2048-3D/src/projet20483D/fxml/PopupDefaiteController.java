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
import projet20483D.Grille3D;

public class PopupDefaiteController implements Initializable {

    @FXML
    private Button boutonMenuDefaite;
    @FXML
    private Label labelScoreDefaite;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //labelScoreDefaite.setText(Integer.toString(getScoreFX()));
    }

    @FXML
    private void cliquerBoutonDefaite(MouseEvent event) throws IOException {
        //ouverture nouvelle page

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));
        Stage stage = (Stage) boutonMenuDefaite.getScene().getWindow();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/fxml/themeClassique.css");

        stage.setScene(scene);
        stage.show();
    }

}
