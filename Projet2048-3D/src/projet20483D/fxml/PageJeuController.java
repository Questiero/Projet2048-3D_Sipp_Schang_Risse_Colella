/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projet20483D.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import projet20483D.Case;
import projet20483D.Direction;
import projet20483D.Grille3D;
import static projet20483D.Parametres.TAILLE;

/**
 * FXML Controller class
 *
 * @author Amandine S
 */
public class PageJeuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button boutonRetourPageJeu, boutonJouerJeu, boutonUP, boutonDOWN, boutonRIGHT, boutonLEFT, boutonFRONT, boutonBACK, boutonRANDOM, boutonIA, boutonANNULER;
    @FXML
    private Label label00_G0, label10_G0, label20_G0, label01_G0, label11_G0, label21_G0, label02_G0, label12_G0, label22_G0;
    @FXML
    private Label label00_G1, label10_G1, label20_G1, label01_G1, label11_G1, label21_G1, label02_G1, label12_G1, label22_G1;
    @FXML
    private Label label00_G2, label10_G2, label20_G2, label01_G2, label11_G2, label21_G2, label02_G2, label12_G2, label22_G2;
    @FXML
    private Label labelScore, labelMeilleurScore;
    @FXML
    private TextArea texteCommentJouer;
    @FXML
    private Pane paneBoutonsIA;
    @FXML
    private ChoiceBox choiceBoxScore;

    private Grille3D g = new Grille3D();

    //création d'une liste pour les labels de nos grilles
    private ArrayList<Label> listeLabels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ajouts des labels dans la liste
        listeLabels.add(label00_G0);
        listeLabels.add(label10_G0);
        listeLabels.add(label20_G0);
        listeLabels.add(label01_G0);
        listeLabels.add(label11_G0);
        listeLabels.add(label21_G0);
        listeLabels.add(label02_G0);
        listeLabels.add(label12_G0);
        listeLabels.add(label22_G0);

        listeLabels.add(label00_G1);
        listeLabels.add(label10_G1);
        listeLabels.add(label20_G1);
        listeLabels.add(label01_G1);
        listeLabels.add(label11_G1);
        listeLabels.add(label21_G1);
        listeLabels.add(label02_G1);
        listeLabels.add(label12_G1);
        listeLabels.add(label22_G1);

        listeLabels.add(label00_G2);
        listeLabels.add(label10_G2);
        listeLabels.add(label20_G2);
        listeLabels.add(label01_G2);
        listeLabels.add(label11_G2);
        listeLabels.add(label21_G2);
        listeLabels.add(label02_G2);
        listeLabels.add(label12_G2);
        listeLabels.add(label22_G2);

        //Désactivation des boutons de déplacement avant le lancement de la partie
        boutonUP.setDisable(true);
        boutonDOWN.setDisable(true);
        boutonRIGHT.setDisable(true);
        boutonLEFT.setDisable(true);
        boutonBACK.setDisable(true);
        boutonFRONT.setDisable(true);
        boutonRANDOM.setDisable(true);
        boutonIA.setDisable(true);
        boutonANNULER.setDisable(true);

        //Affichage meilleur score dès le début
        //labelMeilleurScore.setText(Integer.toString(this.g.getMeilleurScore()));
        
        choiceBoxScore.setItems(FXCollections.observableArrayList("POINTS", "TEMPS", "COUPS"));
    }

    @FXML
    private void cliquerBoutonRetourPageJeu(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageJeu.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/fxml/themeClassique.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void lancerPartie(MouseEvent event) throws IOException {

        //disparition du bouton jouer
        boutonJouerJeu.setVisible(false);
        boutonJouerJeu.setDisable(true);

        g = jeu(g);

        //activation des boutons de déplacement
        boutonUP.setDisable(false);
        boutonDOWN.setDisable(false);
        boutonRIGHT.setDisable(false);
        boutonLEFT.setDisable(false);
        boutonBACK.setDisable(false);
        boutonFRONT.setDisable(false);
        boutonRANDOM.setDisable(false);
        boutonIA.setDisable(false);
        boutonANNULER.setDisable(false);
    }

    @FXML
    private Grille3D jeu(Grille3D g) {

        //création du début du jeu (grilles)
        g.nouvelleCase();
        affichageUpdate(g);  
        
        labelScore.setText(Integer.toString(this.g.getScore()));

        return (g);
    }

    @FXML
    private void affichageUpdate(Grille3D g) {
        //mise dans un tableau des valeurs des grilles   
        //tableau des valeurs des grilles
        int[][][] tab = new int[TAILLE][TAILLE][TAILLE];

        for (Case c : g.getGrille()) {
            tab[c.getY()][c.getX()][c.getZ()] = c.getValeur();
        }

        //écriture des valeurs du tableau (=grille3D) dans nos labels
        ArrayList<Label> listeLabelsCopie = new ArrayList<Label>(listeLabels);  //copie de la liste
        for (int k = 0; k < TAILLE; k++) {
            for (int i = 0; i < TAILLE; i++) {
                for (int j = 0; j < TAILLE; j++) {
                    if (tab[i][j][k] == 0) {
                        listeLabelsCopie.get(0).setText("");    //travail dans la copie                  
                    } else {
                        listeLabelsCopie.get(0).setText(Integer.toString(tab[i][j][k]));    //travail dans la copie

                    }

                    listeLabelsCopie.get(0).getStyleClass().clear();
                    listeLabelsCopie.get(0).getStyleClass().add("tuiles");

                    switch (tab[i][j][k]) {         //applique styles en fonction de la valeur de la tuile
                        case 2:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile2");
                            break;
                        case 4:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile4");
                            break;
                        case 8:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile8");
                            break;
                        case 16:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile16");
                            break;
                        case 32:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile32");
                            break;
                        case 64:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile64");
                            break;
                        case 128:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile128");
                            break;
                        case 256:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile256");
                            break;
                        case 512:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile512");
                            break;
                        case 1024:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile1024");
                            break;
                        case 2048:
                            listeLabelsCopie.get(0).getStyleClass().add("tuile2048");
                            break;
                    }
                    if ((tab[i][j][k]) > 2048) {
                        listeLabelsCopie.get(0).getStyleClass().add("tuilePlus");

                    }

                    listeLabelsCopie.remove(0);
                }
            }
        }
    }

    @FXML
    private void deplacer(Direction dir) throws IOException {

        if (!g.partieFinie()) {

            if (this.g.lanceurDeplacerCases(dir)) {
                this.g.nouvelleCase();
                affichageUpdate(this.g);

                labelScore.setText(Integer.toString(this.g.getScore()));
                // labelMeilleurScore.setText(Integer.toString(this.g.getMeilleurScore()));
            }
        }

        if (g.partieFinie()) {
            if (g.isVictory()) {
                popupVictoire();
            } else {
                popupDefaite();
            }
        }
    }

    private void popupDefaite() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("RESULTATS");

        alert.setHeaderText("DEFAITE");
        alert.setContentText("Vous avez perdu, votre score était de : " + Integer.toString(g.getScore()));

        alert.showAndWait();

        boutonIA.setDisable(true);
        boutonANNULER.setDisable(true);
        boutonUP.setDisable(true);
        boutonDOWN.setDisable(true);
        boutonRIGHT.setDisable(true);
        boutonLEFT.setDisable(true);
        boutonBACK.setDisable(true);
        boutonFRONT.setDisable(true);
        boutonRANDOM.setDisable(true);
        boutonIA.setDisable(true);
        boutonANNULER.setDisable(true);
    }

    private void popupVictoire() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("RESULTATS");
        
        alert.setHeaderText("VICTOIRE");
        alert.setContentText("Vous avez gagné, votre score était de : " + Integer.toString(g.getScore()) + ".\nMais vous pouvez encore continuer à jouer !");

        alert.showAndWait();
    }

    @FXML
    private void cliquerUP(MouseEvent event) throws IOException {
        deplacer(Direction.UP);
    }

    @FXML
    private void clavierDirection(KeyEvent event) throws IOException {
        switch (event.getText()) {
            case "Z", "z":
                deplacer(Direction.UP);
                break;
            case "S", "s":
                deplacer(Direction.DOWN);
                break;
            case "D", "d":
                deplacer(Direction.RIGHT);
                break;
            case "q", "Q":
                deplacer(Direction.LEFT);
                break;
            case "R", "r":
                deplacer(Direction.FRONT);
                break;
            case "F", "f":
                deplacer(Direction.BACK);
                break;
            case "A", "a":
                deplacer(Direction.random());
                break;

        }

        //déplacements avec flèches directionnelles
        /*panePageJeu.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    deplacer(Direction.UP);

                    break;
                case DOWN:
                    deplacer(Direction.DOWN);

                    break;
                case LEFT:
                    deplacer(Direction.LEFT);

                    break;
                case RIGHT:
                    deplacer(Direction.RIGHT);

                    break;
            }
        });*/
    }

    @FXML
    private void cliquerDOWN(MouseEvent event) throws IOException {
        deplacer(Direction.DOWN);
    }

    @FXML
    private void cliquerRIGHT(MouseEvent event) throws IOException {
        deplacer(Direction.RIGHT);
    }

    @FXML
    private void cliquerLEFT(MouseEvent event) throws IOException {
        deplacer(Direction.LEFT);
    }

    @FXML
    private void cliquerFRONT(MouseEvent event) throws IOException {
        deplacer(Direction.FRONT);
    }

    @FXML
    private void cliquerBACK(MouseEvent event) throws IOException {
        deplacer(Direction.BACK);
    }

    @FXML
    private void cliquerRANDOM(MouseEvent event) throws IOException {
        deplacer(Direction.random());
    }

    @FXML
    private void passerCommentJouer(MouseEvent event) throws IOException {
        texteCommentJouer.setVisible(true);
    }

    @FXML
    private void sortirCommentJouer(MouseEvent event) throws IOException {
        texteCommentJouer.setVisible(false);
    }
    
    @FXML
    private void passerBoutonIA(MouseEvent event) throws IOException {
        paneBoutonsIA.setVisible(true);
    }

    @FXML
    private void sortirBoutonIA(MouseEvent event) throws IOException {
        paneBoutonsIA.setVisible(false);
        
    }
    
    

}
