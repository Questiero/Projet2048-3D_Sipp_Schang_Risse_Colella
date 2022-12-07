/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet20483D.database;

public class Requete implements ParamBDD {

    private final String host, dbname, username, pwd;
    private Connexion con = null;

    public Requete() {
        this.host = HOST;
        this.dbname = DBNAME;
        this.username = USERNAME;
        this.pwd = PWD;
        this.con = null;
    }

   /* public String inscription(String pseudo, String mdp, String mdp2) {
        try {
            this.openConnexion();
        }
    }*/

    private void openConnexion() {
        String connectUrl = "jdbc:mysql://" + this.host + "/" + this.dbname;
        if (this.con != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.con = DriverManager.getConnection(connectUrl, this.username, this.pwd);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }
    }

    private void closeConnexion() {
        if (this.con != null) {
            try {
                this.con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
