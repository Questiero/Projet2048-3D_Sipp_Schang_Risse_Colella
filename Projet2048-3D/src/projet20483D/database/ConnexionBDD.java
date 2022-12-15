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

/**
 * Classe permettant de tester la BDD
 */
public class ConnexionBDD {

    /**
     * Main permettant de tester la BDD
     * @param args Arguments
     */
    public static void main(String[] args) {
        Connection connect = null;
        ResultSet rs;
        String serverName = "mysql-projet20483d.alwaysdata.net";
        String mydatabase = "projet20483d_bdd";
        String connectUrl = "jdbc:mysql://" + serverName + "/" + mydatabase; 
        String pseudo = "292158";
        String psw = "projet20483d_mdp";
        String query;

        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            connect = DriverManager.getConnection(connectUrl, pseudo, psw);

            Statement stmt = connect.createStatement();

        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // fermeture connexion bdd
            if (connect != null) {
                try {
                    connect.close();
                } catch (Exception e) {
                     }
            }
        }

    }

}
