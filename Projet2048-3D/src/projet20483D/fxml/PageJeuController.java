package projet20483D.fxml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import projet20483D.Case;
import projet20483D.Direction;
import projet20483D.Grille3D;
import static projet20483D.Parametres.TAILLE;
import projet20483D.Projet20483D;
import static projet20483D.Projet20483D.addMemento;
import static projet20483D.Projet20483D.getMemento;
import static projet20483D.Projet20483D.restoreFromMemento;
import projet20483D.database.Requete;
import static projet20483D.database.Utilisateur.u;
import projet20483D.strategies.deplacements.DeplacementContext;
import projet20483D.strategies.deplacements.ExpectimaxDeplacement;

/**
 * Classe Controlleur FXML de la page de jeu
 */
public class PageJeuController implements Initializable {

    @FXML
    private Button boutonRetourPageJeu, boutonJouerJeu, boutonUP, boutonDOWN, boutonRIGHT, boutonLEFT, boutonFRONT, boutonBACK, boutonRANDOM, boutonIA, boutonANNULER;
    @FXML
    private Label label00_G0, label10_G0, label20_G0, label01_G0, label11_G0, label21_G0, label02_G0, label12_G0, label22_G0;
    @FXML
    private Label label00_G1, label10_G1, label20_G1, label01_G1, label11_G1, label21_G1, label02_G1, label12_G1, label22_G1;
    @FXML
    private Label label00_G2, label10_G2, label20_G2, label01_G2, label11_G2, label21_G2, label02_G2, label12_G2, label22_G2;
    @FXML
    private Label labelScore, labelMeilleurScore, labelAffichagePseudoJeu;
    @FXML
    private TextArea texteCommentJouer;
    @FXML
    private Pane paneBoutonsIA, panePontVisu;
    @FXML
    private ChoiceBox choiceBoxScore;

    private Grille3D g = new Grille3D();
    private boolean annuler = true;
    private int nbAnnuler = 5;
    private boolean continuer = false;
    private long initTimeScore;
    private long minutes = 0;
    private boolean b = false; //utile pour le chrono
    private DeplacementContext context;
    private boolean stop = true;
    private Requete r = new Requete();

    //cr??ation d'une liste pour les labels de nos grilles
    private ArrayList<Label> listeLabels = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        labelAffichagePseudoJeu.setText(u.getPseudo());

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

        //D??sactivation des boutons de d??placement avant le lancement de la partie
        boutonUP.setDisable(true);
        boutonDOWN.setDisable(true);
        boutonRIGHT.setDisable(true);
        boutonLEFT.setDisable(true);
        boutonBACK.setDisable(true);
        boutonFRONT.setDisable(true);
        boutonRANDOM.setDisable(true);
        boutonIA.setDisable(true);
        boutonANNULER.setDisable(true);

        //Affichage meilleur score d??s le d??but
        //labelMeilleurScore.setText(Integer.toString(this.g.getMeilleurScore()));
        choiceBoxScore.setItems(FXCollections.observableArrayList("POINTS", "TEMPS", "COUPS"));
        choiceBoxScore.getSelectionModel().select(0);

    }

    @FXML
    private void cliquerBoutonRetourPageJeu(MouseEvent event) throws IOException {
        //ouverture nouvelle page
        Stage stage;
        Parent root;

        stage = (Stage) boutonRetourPageJeu.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("pageAccueil.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("projet20483D/css/themeClassique.css");
        stage.setScene(scene);
        stage.show();

        g.serialisation();

        if (g.getScore() > u.getMeilleurScore()) {
            u.setMeilleurScore(g.getScore());
            r.updateScoreMax(g.getScore());
        }
    }

    @FXML
    private void lancerPartie(MouseEvent event) throws IOException {
        try {
            FileInputStream fichierIn = new FileInputStream("donnees.ser");
            if (fichierIn.available() != 0) {
                deserialisation();
            } else {
                System.out.println("vide");
                g = jeu(g);
            }
        } catch (java.io.EOFException e) {
            e.printStackTrace();
        }
        //cr??ation timer pour score
        initTimeScore = System.currentTimeMillis();

        //disparition du bouton jouer
        boutonJouerJeu.setVisible(false);
        boutonJouerJeu.setDisable(true);

        //activation des boutons de d??placement
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

        //cr??ation du d??but du jeu (grilles)
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

        //??criture des valeurs du tableau (=grille3D) dans nos labels
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

        // u.setMeilleurScore(g.getScore());
        addMemento(new Grille3D.Memento(g));

        if (this.g.lanceurDeplacerCases(dir)) {
            this.g.nouvelleCase();
            affichageUpdate(this.g);

            Object selectedItems = choiceBoxScore.getSelectionModel().getSelectedItem();

            switch (String.valueOf(selectedItems)) {
                case "POINTS":
                    labelScore.setText(Integer.toString(this.g.getScore()));
                    labelMeilleurScore.setText(Integer.toString(u.getMeilleurScore()));

                    break;
                case "COUPS":
                    labelScore.setText(Integer.toString(this.g.getNbCoups()));
                    break;
                case "TEMPS": //ne s'actualise qu'?? chaque mouvement

                    long scoreTempsSeconde = ((System.currentTimeMillis() - initTimeScore)) / 1000;

                    if (b) {
                        scoreTempsSeconde = (((System.currentTimeMillis() - initTimeScore)) / 1000) - minutes * 60;
                    }

                    if (scoreTempsSeconde >= 60) {
                        minutes++;
                        scoreTempsSeconde -= 60;
                        b = true;
                    }

                    //affichage plus optimis??
                    if (minutes < 10) {
                        if (scoreTempsSeconde < 10) {
                            labelScore.setText("0" + minutes + " : " + "0" + scoreTempsSeconde);
                        } else {
                            labelScore.setText("0" + minutes + " : " + scoreTempsSeconde);
                        }
                    } else {
                        if (scoreTempsSeconde < 10) {
                            labelScore.setText(minutes + " : " + "0" + scoreTempsSeconde);
                        } else {
                            labelScore.setText(minutes + " : " + scoreTempsSeconde);
                        }
                    }

                    break;

                default:
                    labelScore.setText(Integer.toString(this.g.getScore()));

            }
        } else {
            Projet20483D.savedStates.removeFirst();
        }

        if (!annuler) {
            boutonANNULER.setDisable(true);
        }

        if (g.partieFinie() && continuer == false) {
            if (g.isVictory()) {
                popupVictoire();
                continuer = true;
            } else {
                popupDefaite();
            }
        }
    }

    private void popupDefaite() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("RESULTATS");

        alert.setHeaderText("DEFAITE");
        alert.setContentText("Vous avez perdu, votre score ??tait de : " + Integer.toString(g.getScore()));

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
        alert.setContentText("Vous avez gagn??, votre score ??tait de : " + Integer.toString(g.getScore()) + ".\nMais vous pouvez encore continuer ?? jouer !");

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
        panePontVisu.setVisible(true);
    }

    @FXML
    private void sortirBoutonIA(MouseEvent event) throws IOException {
        paneBoutonsIA.setVisible(false);
        panePontVisu.setVisible(false);

    }

    @FXML
    private void cliquerChoiceBoxScore(MouseEvent event) throws IOException {
        labelScore.setText("");
    }

    @FXML
    private void cliquerBoutonAnnuler(MouseEvent event) throws IOException {
        if (Projet20483D.savedStates.size() > 0 && nbAnnuler > 0) {
            g = restoreFromMemento(getMemento());
            annuler = false;
            nbAnnuler--;
            boutonANNULER.setText("ANNULER : " + nbAnnuler);
        }
        if (nbAnnuler == 0) {
            boutonANNULER.setDisable(true);
        }
        affichageUpdate(this.g);

    }

    private void toggleStop() {
        stop = !stop;
    }

    /**
     * Cr??ation d'un thread permettant l'affichage des mouvements de l'IA
     */
    private void createAIThread() {

        // Cr??ation d'un Runnable et d'un thread secondaire afin d'obtenir une animation de d??placement du monstre
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        final Future<?>[] f = {null};

        f[0] = exec.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                Direction dir = context.executeStrategy();
                Platform.runLater(new Runnable() { // classe anonyme
                    @Override
                    public void run() {
                        try {
                            deplacer(dir);
                        } catch (IOException ex) {
                            Logger.getLogger(PageJeuController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                );

                if (g.partieFinie() || stop) {
                    this.cancel();
                }

            }

            private void cancel() {

                // Arr??t du thread
                f[0].cancel(false);

                return;
            }

        }, 0, 100, TimeUnit.MILLISECONDS);

    }

    @FXML
    private void cliquerIA_NAIVE_PlayStop(MouseEvent event) throws IOException {

        context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxDeplacement.ExpectimaxType.NAIVE));

        Direction dir = context.executeStrategy();
        deplacer(dir);

    }

    @FXML
    private void cliquerIA_NAIVE_Entier(MouseEvent event) throws IOException {

        context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxDeplacement.ExpectimaxType.NAIVE));

        this.toggleStop();
        this.createAIThread();

    }

    @FXML
    private void cliquerIA_EMPTY_PlayStop(MouseEvent event) throws IOException {
        context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxDeplacement.ExpectimaxType.EMPTYONLY));

        Direction dir = context.executeStrategy();
        deplacer(dir);

    }

    @FXML
    private void cliquerIA_EMPTY_Entier(MouseEvent event) throws IOException {

        context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxDeplacement.ExpectimaxType.EMPTYONLY));

        this.toggleStop();
        this.createAIThread();

    }

    @FXML
    private void cliquerIA_AVANCEE_PlayStop(MouseEvent event) throws IOException {

        context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxDeplacement.ExpectimaxType.ADVANCED));

        Direction dir = context.executeStrategy();
        deplacer(dir);

    }

    @FXML
    private void cliquerIA_AVANCEE_Entier(MouseEvent event) throws IOException {

        context = new DeplacementContext(new ExpectimaxDeplacement(g, 2, ExpectimaxDeplacement.ExpectimaxType.ADVANCED));

        this.toggleStop();
        this.createAIThread();

    }

    /**
     * D??serialise {@code donnees.ser} dans la Grille3D {@code g}
     */
    public void deserialisation() {

        ObjectInputStream ois = null;
        try {
            FileInputStream fichierIn = new FileInputStream("donnees.ser");
            if (fichierIn.available() != 0) {
                ois = new ObjectInputStream(fichierIn);
                Object o = ois.readObject();

                if (o instanceof Grille3D) {
                    g = (Grille3D) o;
                    affichageUpdate(g);
                } else {
                    g = jeu(g);
                }
            } else {
                System.out.println("Ca ne fonctionne pas");
                g = jeu(g);
            }

        } catch (java.io.EOFException e) {
            e.printStackTrace();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
