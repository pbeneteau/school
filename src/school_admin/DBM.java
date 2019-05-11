package school_admin;

import java.sql.*;
import java.util.ArrayList;

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



    static ArrayList<Cours> getCoursEtudiant(int matriculeEtudiant) {

        ArrayList<Cours> cours = new ArrayList<>(); // Create an ArrayList cours

        try {

            String assisterQuery = "select note_de, note_pjr, note_tp, code_cours from Assister where matricule_etudiant = (?)";

            PreparedStatement assisterStmt = DBM.connection.prepareStatement(assisterQuery);

            assisterStmt.setInt(1, matriculeEtudiant);

            ResultSet rsAssister = assisterStmt.executeQuery();

            while (rsAssister.next()) {

                int code = rsAssister.getInt("code_cours");
                double noteDE = rsAssister.getDouble("note_de");
                double noteTP = rsAssister.getDouble("note_tp");
                double notePJR = rsAssister.getDouble("note_pjr");

                String coursQuery = "select * from Cours where code_cours = (?)";

                PreparedStatement coursStmt = DBM.connection.prepareStatement(coursQuery);

                coursStmt.setInt(1, code);

                ResultSet rsCours = coursStmt.executeQuery();

                rsCours.first();

                String nom = rsCours.getString("nom");
                String description = rsCours.getString("description");
                java.sql.Date date = rsCours.getDate("date");
                float coefficient = rsCours.getFloat("coefficient");
                float coefficientDE = rsCours.getFloat("coefficient_de");
                float coefficientTP = rsCours.getFloat("coefficient_tp");
                float coefficientProjet = rsCours.getFloat("coefficient_projet");

                cours.add(new Cours(code, nom, description, date, coefficient, coefficientDE, coefficientTP, coefficientProjet, noteDE, noteTP, notePJR));
            }
        } catch (SQLException e) { System.out.println(e); return null; }

        return cours;
    }


    static ArrayList<String> getEtudiantPromotion(String promotion) {

        ArrayList<String> ep = new ArrayList<>();

        try {

            Statement stmt = DBM.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select matricule_etudiant from Etudiant where promotion = " + promotion);

            while(rs.next()) {

                ep.add(rs.getString("matricule_etudiant"));
            }
            return ep;

        } catch (SQLException e) { System.out.println(e); return null; }
    }

}
