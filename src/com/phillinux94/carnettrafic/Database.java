package com.phillinux94.carnettrafic;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;



public class Database {

    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement write = null;

    Database(){

        // Connexion à la base
        try {

            Properties properties = new Properties();
            properties.setProperty("characterEncoding", "UTF-8");
            properties.setProperty("encoding", "\"UTF-8\"");

            File fileDatabase = new File("Database/CarnetTrafic.bd");
            String pathDatabase = fileDatabase.getPath();


            this.conn = DriverManager.getConnection("jdbc:sqlite:" + pathDatabase, properties);

            System.out.println("Connexion à la base de données effectuée");

            // Création de la table QSO
            this.stmt = this.conn.createStatement();

            String createTableQso = "CREATE TABLE IF NOT EXISTS QSO " +
                    "(ID INT PRIMARY KEY NOT NULL, " +
                    "QSO_DATE               DATETIME DEFAULT CURRENT_DATE, " +
                    "QSO_INDICATIF          TEXT, " +
                    "QSO_DEPARTEMENT        TEXT, " +
                    "QSO_LOCATOR            TEXT, " +
                    "QSO_BANDE              TEXT, " +
                    "QSO_MODE               TEXT, " +
                    "QSO_QTH                TEXT, " +
                    "QSO_RST_R              TEXT, " +
                    "QSO_RST_E              TEXT, " +
                    "QSO_DISTANCE           INT)";

            this.stmt.execute(createTableQso);

            System.out.println("Table QSO sélectionnée");

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    public void insertQso(int id, LocalDate dateQso, String indicatifQso, String departQso, String locatorQso,
                          String bandeQso, String modeQso, String qthQso, String rst_r, String rst_e, int distance){

        try {

            Date locDateQso = Date.valueOf(dateQso);

            String insertSql =  "INSERT INTO QSO (ID, QSO_DATE, QSO_INDICATIF, QSO_DEPARTEMENT, QSO_LOCATOR, QSO_BANDE,  " +
                    "QSO_MODE, QSO_QTH, QSO_RST_R, QSO_RST_E, QSO_DISTANCE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            this.write = this.conn.prepareStatement(insertSql);

            this.write.setInt(1, id);
            this.write.setDate(2, locDateQso);
            this.write.setString(3, indicatifQso);
            this.write.setString(4, departQso);
            this.write.setString(5, locatorQso);
            this.write.setString(6, bandeQso);
            this.write.setString(7, modeQso);
            this.write.setString(8, qthQso);
            this.write.setString(9, rst_r);
            this.write.setString(10, rst_e);
            this.write.setInt(11, distance);

            this.write.addBatch();

            this.write.executeBatch();

        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }
    }

    public ArrayList getListeQso(String debut, String fin){

        ArrayList listeQso = new ArrayList();

        try {

            this.stmt = this.conn.createStatement();

            String sqlListeQso =    "SELECT * FROM QSO " +
                                    "WHERE strftime('%Y-%m', QSO_DATE / 1000, 'unixepoch') BETWEEN '" + debut +"' AND '" + fin + "' ";

            ResultSet rs = this.stmt.executeQuery(sqlListeQso);

            while (rs.next()){

                ArrayList temp = new ArrayList();
                temp.add(rs.getInt("ID"));
                LocalDate dateQso = rs.getDate("QSO_DATE").toLocalDate();
                temp.add(dateQso);
                temp.add(rs.getString("QSO_INDICATIF"));
                temp.add(rs.getString("QSO_DEPARTEMENT"));
                temp.add(rs.getString("QSO_LOCATOR"));
                temp.add(rs.getString("QSO_BANDE"));
                temp.add(rs.getString("QSO_MODE"));
                temp.add(rs.getString("QSO_QTH"));
                temp.add(rs.getString("QSO_RST_R"));
                temp.add(rs.getString("QSO_RST_E"));
                temp.add(rs.getInt("QSO_DISTANCE"));

                listeQso.add(temp);

            }
        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }

        return listeQso;
    }

    public void deleteQso(int id){

        try {

            this.stmt = this.conn.createStatement();
            this.stmt.executeQuery("DELETE FROM QSO WHERE ID = " + id);

        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }
    }

    public int getMaxId(){

        int maxId = 0;

        try {

            this.stmt = this.conn.createStatement();
            ResultSet rs = this.stmt.executeQuery("SELECT MAX(ID) FROM QSO");

            if (rs.next()){

                maxId = rs.getInt(1);

            }

        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }

        return maxId;
    }

    public ArrayList getListePeriodes(){

        ArrayList datas = new ArrayList();

        try {

            this.stmt = this.conn.createStatement();

            String sqlListePeriodes =   "SELECT DISTINCT(strftime('%Y-%m', QSO_DATE / 1000, 'unixepoch')) AS ANNEE_MOIS " +
                    "FROM QSO " +
                    "ORDER BY ANNEE_MOIS";

            ResultSet rs = this.stmt.executeQuery(sqlListePeriodes);

            while (rs.next()){

                datas.add(rs.getString(1));

            }
        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }

        return datas;

    }

    public ArrayList getStatisticsByDate(String debut, String fin){

        ArrayList datas = new ArrayList();

        try {

            this.stmt = this.conn.createStatement();

            String sqlStatDate =    "SELECT strftime('%Y-%m', QSO_DATE / 1000, 'unixepoch') AS ANNEE_MOIS, COUNT(*) " +
                    "FROM QSO " +
                    "WHERE ANNEE_MOIS BETWEEN '" + debut +"' AND '" + fin + "' " +
                    "GROUP BY ANNEE_MOIS " +
                    "ORDER BY ANNEE_MOIS";

            ResultSet rs = this.stmt.executeQuery(sqlStatDate);

            while (rs.next()){

                ArrayList temp = new ArrayList();

                temp.add(rs.getString(1));
                temp.add(rs.getInt(2));

                datas.add(temp);
            }

        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }

        return datas;
    }



    public ArrayList getStatisticsByDistance(String debut, String fin){

        ArrayList datas = new ArrayList();

        try {

            this.stmt = this.conn.createStatement();

            String sqlStatDistance =    "SELECT CASE " +
                    "WHEN QSO_DISTANCE < 50 THEN '1 - Moins de 50 kilomètres' " +
                    "WHEN QSO_DISTANCE >= 50 AND QSO_DISTANCE < 300 THEN '2 - De 50 à 299 kilomètres' " +
                    "WHEN QSO_DISTANCE >= 300 AND QSO_DISTANCE < 1000 THEN '3 - De 300 à 999 kilomètres' " +
                    "WHEN QSO_DISTANCE >= 1000 AND QSO_DISTANCE < 5000 THEN '4 - De 1000 à 4999 kilomètres' " +
                    "WHEN QSO_DISTANCE >= 5000 AND QSO_DISTANCE < 10000 THEN '5 - De 5000 à 9999 kilomètres' " +
                    "WHEN QSO_DISTANCE >= 10000 THEN '6 - De 10000 kilomètres et plus' " +
                    "END AS TRANCHE, " +
                    "COUNT(*) " +
                    "FROM QSO " +
                    "WHERE strftime('%Y-%m', QSO_DATE / 1000, 'unixepoch') BETWEEN '" + debut +"' AND '" + fin + "' " +
                    "GROUP BY TRANCHE";

            ResultSet rs = this.stmt.executeQuery(sqlStatDistance);

            while (rs.next()){

                ArrayList temp = new ArrayList();

                temp.add(rs.getString(1));
                temp.add(rs.getInt(2));

                datas.add(temp);
            }

        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }

        return datas;

    }

    public int getNbQso(String debut, String fin){

        int nbQso = 0;

        try {

            this.stmt = this.conn.createStatement();

            String sqlNbQso =   "SELECT COUNT(*) FROM QSO " +
                    "WHERE strftime('%Y-%m', QSO_DATE / 1000, 'unixepoch') BETWEEN '" + debut +"' AND '" + fin + "' ";

            ResultSet rs = this.stmt.executeQuery(sqlNbQso);

            nbQso = rs.getInt(1);

        }
        catch (SQLException e){

            System.out.println(e.getMessage());

        }


        return nbQso;
    }

    public void closeDatabase(){

        System.out.println("Fermeture connexion base de données");

        if (this.write != null){
            try {

                this.write.close();

            }
            catch (SQLException e){}
        }

        if (this.stmt != null){
            try {

                this.stmt.close();

            }
            catch (SQLException e){}
        }

        if (this.conn != null){
            try {

                this.conn.close();

            }
            catch (SQLException e){}
        }
    }
}