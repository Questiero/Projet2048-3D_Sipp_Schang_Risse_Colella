/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet20483D.database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projet20483D.fxml.Utilisateur.u;

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
        try {
            this.openConnexion();
            if (pseudo == null) {
                return "Entrez un pseudo correct";
                //} else if (dejaPris(pseudo)){
                //return "Ce pseudo existe déjà";
            } else if (mdp.length() < 8) {
                return "Mdp trop court";
            } else if (!mdp.equals(mdp2)) {
                return "les mdp ne sont pas identiques";
            } else {

                PreparedStatement stmt = connect.prepareStatement("INSERT INTO user (pseudo, mdp) VALUES (?,?)");
                stmt.setString(1, pseudo);
                stmt.setString(2, hashSha256(mdp));
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
        try {
            this.openConnexion();

            mdp = hashSha256(mdp);  //vérif du salage
            PreparedStatement stmt = connect.prepareStatement("SELECT pseudo, mdp FROM user WHERE pseudo like ? AND mdp like ?");
            stmt.setString(1, pseudo);
            stmt.setString(2, mdp);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                u.setPseudo(pseudo);
                u.setConnecte(true);
                this.getMeilleurScore();
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
    
    public boolean updateScore(int score) {
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
        if (this.connect != null) {
            try {
                this.connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean dejaPris(String username) {
        try {
            boolean res = true;
            PreparedStatement stmt = connect.prepareStatement("SELECT count() FROM user WHERE pseudo like ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            if (rs.getInt("count()") > 0) {//le nom d'utilisateur existe deja
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
    
    public void getMeilleurScore() {
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
    
    
    private String hashSha256(String pwd) {     //salage mdp
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
