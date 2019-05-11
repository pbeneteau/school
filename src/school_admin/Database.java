package school_admin;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")

class Database {

    private static Connection connection;

    static boolean init() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://35.233.50.222:3306/bdd?useSSL=false", "root", "poiuytre");
            System.out.println("Connecté à la base de donnée.\n");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur connection base de donnée: " + e + "\n");
            return false;
        }
    }

    static ArrayList<Administrateur> getAdmins() {

        ArrayList<Administrateur> administrateurs = new ArrayList<>(); // Create an ArrayList admins

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Administrateur");

            while(rs.next()) {

                int matricule = rs.getInt("numero_administateur");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String tel = rs.getString("tel");

                administrateurs.add(new Administrateur(matricule, nom, prenom, tel, email));
            }
        } catch (SQLException e) { System.out.println(e); return null; }

        return administrateurs;
    }

    static Etudiant getEtudiant(int matriculeEtudiant) {

        Etudiant etudiant; // Create an ArrayList admins

        try {

            String query = "select * from Etudiant where matricule_etudiant = ?";

            PreparedStatement stmt = Database.connection.prepareStatement(query);

            stmt.setInt(1, matriculeEtudiant);

            ResultSet rs=stmt.executeQuery();

            rs.first();

            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String sexe = rs.getString("sexe");
            java.sql.Date date_naissance = rs.getDate("date_naissance");
            String paysNaissance = rs.getString("pays_naissance");
            String villeNaissance = rs.getString("ville_naissance");
            String photo = rs.getString("photo");
            String numeroRue = rs.getString("numero_rue") ;
            String nomRue = rs.getString("nom_rue");
            String codePostale = rs.getString("code_postal");
            String ville = rs.getString("ville");
            String tel = rs.getString("tel");
            String email = rs.getString("email");

            ArrayList<Cours> cours = getCoursEtudiant(matriculeEtudiant);
            etudiant = new Etudiant(matriculeEtudiant, nom, prenom, sexe, date_naissance, paysNaissance, villeNaissance, photo, numeroRue, nomRue, codePostale, ville, cours, tel, email);

        } catch (SQLException e) { System.out.println(e); return null; }

        return etudiant;
    }

    static GroupeEleve getGroupe(int numeroGroupe) {

        GroupeEleve groupe;

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Groupe_eleve where " + numeroGroupe + "= numero_groupe");

            rs.first();
            Cours cours = getCours(rs.getInt("numero_cours"));
            groupe = new GroupeEleve(numeroGroupe, cours);

        } catch (SQLException e) { System.out.println(e); return null;}

        return groupe;
    }

    static boolean getNotesEtudiant (int matriculeEtudiant) {

        try {

            String query = "select note_de, note_tp, note_pjr from Assister where matricule_etudiant = (?)";

            PreparedStatement stmt = Database.connection.prepareStatement(query);
            stmt.setInt(1, matriculeEtudiant);
            stmt.execute(query);

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


    static ArrayList<Cours> getAllCours() {

        ArrayList<Cours> cours = new ArrayList<>();

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cours");

            rs.first();
            int code = rs.getInt("code_cours");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            java.sql.Date date = rs.getDate("date");
            double coefficient = rs.getFloat("coefficient");
            double coefficientDE = rs.getFloat("coefficient_de");
            double coefficientTP = rs.getFloat("coefficient_tp");
            double coefficientProjet = rs.getFloat("coefficient_projet");

            cours.add(new Cours(code, nom, description, date, coefficient, coefficientDE, coefficientTP, coefficientProjet));

        } catch (SQLException e) { System.out.println(e); return null; }

        return cours;
    }

    static Cours getCours(int codeCours) {

        Cours cours;

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cours where " + codeCours + "= code_cours");

            rs.first();
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            java.sql.Date date = rs.getDate("date");
            double coefficient = rs.getFloat("coefficient");
            double coefficientDE = rs.getFloat("coefficient_de");
            double coefficientTP = rs.getFloat("coefficient_tp");
            double coefficientProjet = rs.getFloat("coefficient_projet");

            cours = new Cours(codeCours, nom, description, date, coefficient, coefficientDE, coefficientTP, coefficientProjet);

        } catch (SQLException e) { System.out.println(e); return null; }

        return cours;
    }


    static ArrayList<Cours> getCoursEtudiant(int matriculeEtudiant) {

        ArrayList<Cours> cours = new ArrayList<>(); // Create an ArrayList cours

        try {

            String assisterQuery = "select note_de, note_pjr, note_tp, code_cours from Assister where matricule_etudiant = (?)";

            PreparedStatement assisterStmt = Database.connection.prepareStatement(assisterQuery);

            assisterStmt.setInt(1, matriculeEtudiant);

            ResultSet rsAssister = assisterStmt.executeQuery();

            while (rsAssister.next()) {

                int code = rsAssister.getInt("code_cours");
                double noteDE = rsAssister.getDouble("note_de");
                double noteTP = rsAssister.getDouble("note_tp");
                double notePJR = rsAssister.getDouble("note_pjr");

                String coursQuery = "select * from Cours where code_cours = (?)";

                PreparedStatement coursStmt = Database.connection.prepareStatement(coursQuery);

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

    static ArrayList<Professeur> getProfesseurs() {

        ArrayList<Professeur> professeurs = new ArrayList<>(); // Create an ArrayList professeurs

        try {

            Statement stmt= Database.connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Professeur");

            while(rs.next()) {

                int matricule = rs.getInt("matricule_professeur");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String numeroRue = rs.getString("numero_rue") ;
                String nomRue = rs.getString("nom_rue");
                String codePostal = rs.getString("code_postal");
                String ville = rs.getString("ville");
                String tel = rs.getString("tel");
                String email = rs.getString("email");

                professeurs.add(new Professeur(matricule, nom, prenom, numeroRue, nomRue, codePostal, ville, tel, email));
            }
        } catch (SQLException e) { System.out.println(e); return null; }

        return professeurs;
    }


    static ArrayList<PersonneResponsable> getPersonneResponsable() {

        ArrayList<PersonneResponsable> personneResponsables = new ArrayList<>(); // Create an ArrayList PersonneResponsable

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Personne_responsable");

            while(rs.next()) {

                int num = rs.getInt("numero_pr");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String numeroRue = rs.getString("numero_rue") ;
                String nomRue = rs.getString("nom_rue");
                String ville = rs.getString("ville");
                String pays = rs.getString("pays");
                String codePostal = rs.getString("code_postal");

                personneResponsables.add(new PersonneResponsable(num,  prenom,  nom,  email,  tel,  numeroRue,  nomRue,  ville,  pays, codePostal));
            }
        } catch (SQLException e) { System.out.println(e); return null; }

        return personneResponsables;
    }


    static boolean creerGroupe (int numero_groupe, int numero_cours) {

        try {

            String queryg = "Insert into Groupe_eleve (numero_groupe, numero_cours)"
                    + " values (?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(queryg);

            preparedStmt.setInt(1, numero_groupe);
            preparedStmt.setInt(2, numero_cours);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


    static boolean majEleveGroup (int numero_groupe) {

        try {

            Statement stmt = Database.connection.createStatement();


            String query = "UPDATE Etudiant set numero_groupe = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, numero_groupe);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }
        return true;
    }

    static boolean retirerEleveGroup () {

        try {

            String query = "UPDATE Etudiant set numero_groupe = NULL";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }



    static boolean creerCours(int code, String nom, String description, String date, double coefficient, double coefficentDE, double coefficientTP, double coefficientProjet) {

        try {

            // Creation d'un nouveau Cours
            String query = " Insert into Cours ( code_cours, nom, description, date, coefficient, coefficient_de, coefficient_tp, coefficient_projet)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, code);
            preparedStmt.setString(2, nom);
            preparedStmt.setString(3, description);
            preparedStmt.setString(4, date);
            preparedStmt.setDouble(5, coefficient);
            preparedStmt.setDouble(6, coefficentDE);
            preparedStmt.setDouble(7, coefficientTP);
            preparedStmt.setDouble(8, coefficientProjet);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }



    static boolean associateCoursGroup(int codeCours) {

        try {

            String query = "Insert into Groupe_eleve(numero_cours)" + "values(?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, codeCours);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


    // gestion note uniquement par prof du cours
    static boolean ajouterNote (int codeCours, int matriculeProf, double note_de, double note_tp, double note_pjr) {

        try {

            String query = "INSERT into Assister (matricule_etudiant, code_cours ,note_de, note_tp, note_pjr)" + "values (?,?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1, codeCours);
            preparedStmt.setInt(2, matriculeProf);
            preparedStmt.setDouble(3, note_de);
            preparedStmt.setDouble(4, note_tp);
            preparedStmt.setDouble(5, note_pjr);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


    // mise a jour uniquement par personne scola sur demande prof
    static boolean majNoteEleve (int matricule, double note_de, double note_tp, double note_pjr) {

        try {

            String query = "UPDATE Assister set note_de = ?, note_tp = ?, note_pjr = ? where matricule_etudiant = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setDouble(1, note_de);
            preparedStmt.setDouble(2, note_tp);
            preparedStmt.setDouble(3, note_pjr);
            preparedStmt.setInt(4, matricule);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }

    static int creerIdentite(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue) {

        int id = 404;

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

            query = "select id_identite from Identite where email = " + email;

            Statement stmt= Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            rs.first();

            return rs.getInt("id_identite");

        } catch (SQLException e) { System.out.println(e); return id; }
    }

    static boolean creerEtudiant(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue, int password, int numeroGroupe) {

        try {

            int identiteID = creerIdentite(nom, prenom, sexe, photo, dateNaissance, paysNaissance, villeNaissance, tel, email, ville, codePostal, numeroRue, nomRue);

            String query = "Insert into Etudiant (password, numero_groupe, id_identite) values (?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1, password);
            preparedStmt.setInt(2, numeroGroupe);
            preparedStmt.setInt(3, identiteID);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }

    static boolean creerProfesseur(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue, int password) {

        try {

            int identiteID = creerIdentite(nom, prenom, sexe, photo, dateNaissance, paysNaissance, villeNaissance, tel, email, ville, codePostal, numeroRue, nomRue);

            String query = "Insert into Professeur (id_identite, password) values (?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setInt(1, identiteID);
            preparedStmt.setInt(2, password);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


}