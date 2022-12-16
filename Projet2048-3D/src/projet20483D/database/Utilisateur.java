package projet20483D.database;

/**
 * Classe représentant un utilisateur
 */
public class Utilisateur {

    /**
     * Pseudonyme de l'utilisateur
     */
    private String pseudo;
    /**
     * Etat de la connection
     */
    private boolean connecte;
    /**
     * Meilleur score
     */
    private int meilleurScore;

    /**
     * Singleton
     */
    public static Utilisateur u = new Utilisateur();

    /**
     * Création d'un utilisateur par défaut
     */
    public Utilisateur() {
        this.pseudo = "";
        this.meilleurScore = 0;
    }

    /**
     * Retourne le meilleur score de l'utilisateur
     *
     * @return meilleur score
     */
    public int getMeilleurScore() {
        return meilleurScore;
    }

    /**
     * Change le meilleur score de l'utilisateur
     *
     * @param meilleurScore Meilleur score
     */
    public void setMeilleurScore(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    /**
     * Retourne le pseudonyme de l'utilisateur
     *
     * @return Pseudonyme
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Change le pseudonyme de l'utilisateur
     *
     * @param pseudo Pseudonyme
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Renvoie l'état de la connection
     *
     * @return Etat de la connection
     */
    public boolean isConnecte() {
        return connecte;
    }

    /**
     * Change l'état de la connection
     *
     * @param connecte Etat de la connection
     */
    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

}
