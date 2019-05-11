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

    static boolean creerIndentite(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue) {


        try {

            // Creation d'un nouveau Cours
            String query = "Insert into Identite(nom, prenom, sexe, photo, date_naissance, pays_naissance, ville_naissance, tel, email, ville, code_postal, numero_rue, nom_rue) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, nom);
            preparedStmt.setString(2, prenom);
            preparedStmt.setString(3, String.valueOf(sexe));
            preparedStmt.setBlob(4, photo);
            preparedStmt.setDate(5, dateNaissance);
            preparedStmt.setString(6, paysNaissance);
            preparedStmt.setString(7, villeNaissance);
            preparedStmt.setString(8, tel);
            preparedStmt.setString(9, email);
            preparedStmt.setString(10, ville);
            preparedStmt.setString(11, codePostal);
            preparedStmt.setString(12, numeroRue);
            preparedStmt.setString(13, nomRue);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }
        return true;
    }





}
