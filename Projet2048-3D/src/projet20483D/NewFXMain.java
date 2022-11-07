/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projet20483D;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author risse
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("projet20483D/pageAccueil.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/theme.css"); //test mais pb? à A REVOIR
        //boolean add = scene.getStylesheets().add("css/styles.css");
        stage.setScene(scene);
        stage.show();   
        
    }

    public static void main(String[] args) {
    launch(args);
    }
}
