package projet20483D;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale permettant le lancement de l'interface graphique
 */
public class InterfaceGraphique extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/fxml/pageAccueil.fxml"));
        Scene scene = new Scene(root);

        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        //stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fonction principale permettant de lancer l'interface graphique
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
