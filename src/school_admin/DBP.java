package school_admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@SuppressWarnings("Duplicates")


public class DBP {


    private static Connection connection;

    static boolean init() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://35.233.50.222:3306/bdd?useSSL=false", "root", "poiuytre");
            System.out.println("Connecté à la base de donnée de Polo.\n");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur connection base de donnée: " + e + "\n");
            return false;
        }
    }

    static boolean creerCours(String nom, String description, java.sql.Date date, double coefficient, double coefficentDE, double coefficientTP, double coefficientProjet, int matriculeProfesseur) {

        try {

            // Creation d'un nouveau Cours
            String query = "Insert into Cours(nom, description, date, coefficient, coefficient_de, coefficient_tp, coefficient_projet, matricule_professeur)"
                    + " values(?, ?, ?, ?, ?, ?, ?, (select matricule_professeur from Professeur where matricule_professeur =" + matriculeProfesseur + "))";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, nom);
            preparedStmt.setString(2, description);
            preparedStmt.setDate(3, date);
            preparedStmt.setDouble(4, coefficient);
            preparedStmt.setDouble(5, coefficentDE);
            preparedStmt.setDouble(6, coefficientTP);
            preparedStmt.setDouble(7, coefficientProjet);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }







}
