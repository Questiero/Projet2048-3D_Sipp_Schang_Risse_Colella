/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet20483D.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projet20483D.database.Utilisateur.u;

public class Requete implements ParamBDD {

    private final String host, dbname, username, pwd;
    private Connection connect = null;

    public Requete() {
        this.host = HOST;
        this.dbname = DBNAME;
        this.username = USERNAME;
        this.pwd = PWD;
        this.connect = null;
    }

    public String inscription(String pseudo, String mdp, String mdp2) {
        //permet l'inscription d'un nouvel utilisateur dans la BDD, en vérifiant si le mdp existe déjà ou non et en vérifiant le mdp et sa confirmation
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

    public boolean connexion(String pseudo, String mdp) {
        //permet la connexion d'un utilisateur, en vérifiant si le mdp et le pseudo correspondent
        
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

    private void openConnexion() {
        //ouvre la connexion (se connecte à la BDD)
        String connectUrl = "jdbc:mysql://" + this.host + "/" + this.dbname;
        if (this.connect != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.connect = DriverManager.getConnection(connectUrl, this.username, this.pwd);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }
    }

    public boolean updateScoreMax(int score) {
        //vérifie si le score actuel est plus élevé ou non que le scoreMax dans la BDD
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

    private void closeConnexion() {
        //ferme la connexion avec la bdd (deconnexion de la bdd)
        if (this.connect != null) {
            try {
                this.connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

    public void getScoreMax() {
        //avoir score maximum (en points) de l'utilisateur + set du meilleur score (en points)
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

    
    public String getClassement() {
        //donne le classement des 5 meilleurs score (en points)
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
