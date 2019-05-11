package school_admin;

import java.sql.*;

@SuppressWarnings("Duplicates")


public class DBM {

    private static Connection connection;

    static boolean init() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://35.233.50.222:3306/bdd?useSSL=false", "root", "poiuytre");
            System.out.println("Connecté à la base de donnée de Marco.\n");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur connection base de donnée: " + e + "\n");
            return false;
        }
    }

    static boolean creerGroupe (int numero_cours) {

        try {

            String queryg = "Insert into Groupe_eleve (numero_cours)"
                    + " values (?)";

            PreparedStatement preparedStmt = connection.prepareStatement(queryg);

            preparedStmt.setInt(1, numero_cours);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }



    static boolean ajouterCoursGroupe (int numeroGroupe, int numeroCours) {

        try {

            String queryg = "Insert into Groupe_eleve (numero_groupe, numero_cours) values (" + numeroGroupe + ", ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(queryg);

            preparedStmt.setInt(1, numeroCours);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


    static boolean changerEleveGroup (int matricule_etudiant, int numero_groupe) {

        try {

            String query = "UPDATE Etudiant set numero_groupe = (?) where matricule_etudiant = (?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1, numero_groupe);
            preparedStmt.setInt(2, matricule_etudiant);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }
        return true;
    }


}
