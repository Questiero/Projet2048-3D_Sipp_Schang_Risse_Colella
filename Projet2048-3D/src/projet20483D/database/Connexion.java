/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projet20483D.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {

    public static void main(String[] args) {
        Connection con = null;
        ResultSet rs;
        String serverName = "mysql-projet20483d.alwaysdata.net";
        String mydatabase = "projet20483d_bdd";
        String connectUrl = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
        String username = "292158";//utilisateur à créer dans la base
        String password = "projet20483d_mdp";//mot de passe de l'utilisateur
        String query;

        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(connectUrl, username, password);
            System.out.println("Database connection established.");

            Statement stmt = con.createStatement();

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("Avez-vous pensé à démarrer Wamp ?");
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        } finally {
            // à la fin, on ferme la connection avec la BdD
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Database connection terminated.");
                } catch (Exception e) {
                    /* ignore close errors */ }
            }
        }

    }

}
