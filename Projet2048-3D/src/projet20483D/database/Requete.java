package projet20483D.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projet20483D.database.Utilisateur.u;

/**
 * Classe permettant l'envoi de requête à la base de donnée
 */
public class Requete implements ParamBDD {

    /**
     * Connection à la base de donnée
     */
    private Connection connect = null;

    /**
     * Permet l'inscription d'un nouvel utilisateur dans la BDD, en vérifiant si
     * le mdp existe déjà ou non et en vérifiant le mdp et sa confirmation
     *
     * @param pseudo Pseudo de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     * @param mdp2 Confirmation du mot de passe de l'utilisateur
     * @return Message d'erreur (si il y en a)
     */
    public String inscription(String pseudo, String mdp, String mdp2) {
        try {
            this.openConnexion();
            if (pseudo == null) {
                return "Entrez un pseudo correct";
            } else if (pseudoUtilise(pseudo)) {
                return "Ce pseudo existe déjà";
            } else if (mdp.length() < 8) {
                return "MOt de passe trop court";
            } else if (!mdp.equals(mdp2)) {
                return "Les mots de passes ne sont pas identiques";
            } else {

                PreparedStatement stmt = connect.prepareStatement("INSERT INTO user (pseudo, mdp) VALUES (?,?)");
                stmt.setString(1, pseudo);
                stmt.setString(2, mdp);

                stmt.executeUpdate();
                return "";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "erreur";
        } finally {
            this.closeConnexion();
        }

    }

    /**
     * Permet la connexion d'un utilisateur, en vérifiant si le mdp et le pseudo
     * correspondent
     *
     * @param pseudo Pseudonyme de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     * @return {@code true} si la connection est réussie, {@code false} sinon
     */
    public boolean connexion(String pseudo, String mdp) {

        try {
            this.openConnexion();

            PreparedStatement stmt = connect.prepareStatement("SELECT pseudo, mdp FROM user WHERE pseudo like ? AND mdp like ?");
            stmt.setString(1, pseudo);
            stmt.setString(2, mdp);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u.setPseudo(pseudo);
                u.setConnecte(true);
                System.out.println("connecté");
                System.out.println(u.getPseudo());

                this.getScoreMax();
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            this.closeConnexion();
        }
    }

    /**
     * Ouvre la connexion
     */
    private void openConnexion() {
        String connectUrl = "jdbc:mysql://" + HOST + "/" + DBNAME;
        if (this.connect != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.connect = DriverManager.getConnection(connectUrl, USERNAME, PWD);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }
    }

    /**
     * Vérifie si le score actuel est plus élevé ou non que le scoreMax dans la
     * BDD
     *
     * @param score Score actuel
     * @return {@code true} si le score actuel est plus élevé, {@code false}
     * sinon
     */
    public boolean updateScoreMax(int score) {
        boolean res = false;
        try {
            this.openConnexion();
            PreparedStatement st = connect.prepareStatement("UPDATE user SET scoreMax = ? WHERE pseudo = ?");
            st.setInt(1, score);
            st.setString(2, u.getPseudo());
            if (st.executeUpdate() >= 1) {
                res = true;
            } else {
                res = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erreur");
        } finally {
            this.closeConnexion();
        }
        return res;
    }

    /**
     * Ferme la connexion avec la BDD
     */
    private void closeConnexion() {
        if (this.connect != null) {
            try {
                this.connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Vérifie si le pseudonyme existe déjà dans la BDD
     *
     * @param pseudo Pseudonyme
     * @return {@code true} si il existe déjà, {@code false} sinon
     */
    private boolean pseudoUtilise(String pseudo) {
        //vérifie si le pseudo existe déjà ou non dans la bdd
        try {
            boolean res = true;
            PreparedStatement stmt = connect.prepareStatement("SELECT count(*) FROM user WHERE pseudo like ?");
            stmt.setString(1, pseudo);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            if (rs.getInt("count(*)") > 0) {
                res = true;
            } else {
                res = false;
            }

            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Requete.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Permet d'obtenir le score maximum (en point) de l'utilisateur et de
     * l'actualiser
     */
    public void getScoreMax() {
        try {
            this.openConnexion();
            PreparedStatement st = connect.prepareStatement("SELECT scoreMax FROM user WHERE pseudo = ?");
            st.setString(1, u.getPseudo());
            ResultSet rs = st.executeQuery();
            rs.first();
            u.setMeilleurScore(rs.getInt("scoreMax"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erreur");
        } finally {
            this.closeConnexion();
        }
    }

    /**
     * Retourne le classement des 5 meilleurs scores
     *
     * @return Classement des 5 meilleurs utilisateurs en fonction du score
     */
    public String getClassement() {
        String res = "";
        try {
            this.openConnexion();
            PreparedStatement stmt = connect.prepareStatement("SELECT pseudo, scoreMax FROM user ORDER BY scoreMax DESC LIMIT 5");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                res = res + (rs.getString("pseudo")) + ";" + (rs.getString("scoreMax") + ";");
            }
        } catch (SQLException e) {
            Logger.getLogger(Requete.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnexion();
        }
        return res;
    }
}
