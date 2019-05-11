package school_admin;

import javax.xml.crypto.Data;
import java.awt.image.AreaAveragingScaleFilter;
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

    static Etudiant getEtudiant(int matriculeEtudiant) {

        Etudiant etudiant; // Create an ArrayList admins

        try {

            String query = "select * from Etudiant where matricule_etudiant = (?)";

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

    static boolean getNotesEtudiant (int matriculeEtudiant) {

        try {

            // je sais pas pk ca fonctionne pas
            // String query = "select note_de, note_tp, note_pjr from Assister where " + matriculeEtudiant + "= matricule_etudiant";
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

    static boolean majGroupeEtudiant(int matriculeEtudiant, int numero_groupe) {

        try {

            Statement stmt = Database.connection.createStatement();


            String query = "UPDATE Etudiant set numero_groupe = ? where matricule_etudiant =" + matriculeEtudiant;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, numero_groupe);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }
        return true;
    }

    static ArrayList<Integer> getMatriculeEtudiantsGroupe(int numeroGroupe) {

        ArrayList<Integer> ids = new ArrayList<>();

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select matricule_etudiant from Etudiant where numero_groupe =" + numeroGroupe);

            while(rs.next()) {

                ids.add(rs.getInt("matricule_etudiant"));
            }
            return ids;

        } catch (SQLException e) { System.out.println(e); return null; }
    }

    static ArrayList<Integer> getMatriculeEtudiantsPromotion(String promotion) {

        ArrayList<Integer> ids = new ArrayList<>();

        try {

            PreparedStatement stmt = Database.connection.prepareStatement("select matricule_etudiant from Etudiant where promotion = ?");

            stmt.setString(1, promotion);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                ids.add(rs.getInt("matricule_etudiant"));
            }
            return ids;

        } catch (SQLException e) { System.out.println(e); return null; }
    }

    static boolean retirerEleveGroup() {

        try {

            String query = "UPDATE Etudiant set numero_groupe = NULL";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }



    static boolean creerCours(String nom, String description, java.sql.Date date, double coefficient, double coefficentDE, double coefficientTP, double coefficientProjet, int matriculeProfesseur) {

        try {

            // Creation d'un nouveau Cours
            String query = " Insert into Cours(nom, description, date, coefficient, coefficient_de, coefficient_tp, coefficient_projet, matricule_professeur)"
                    + " values (?, ?, ?, ?, ?, ?, ?, (select matricule_professeur from Professeur where matricule_professeur =" + matriculeProfesseur+ "))";

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


    // gestion note uniquement par prof du cours
    static boolean ajouterNotesEtudiant(int codeCours, int matriculeEtudiant, double note_de, double note_tp, double note_pjr) {

        try {

            String query = "INSERT into Assister(matricule_etudiant, code_cours, note_de, note_tp, note_pjr)" + "values ((select matricule_etudiant from Etudiant where matricule_etudiant = " + matriculeEtudiant + "), (select code_cours from Cours where code_cours =" + codeCours + "),?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setDouble(1, note_de);
            preparedStmt.setDouble(2, note_tp);
            preparedStmt.setDouble(3, note_pjr);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }


    // mise a jour uniquement par personne scola sur demande prof
    static boolean majNotesEtudiant(int matriculeEtudiant, int codeCours, double note_de, double note_tp, double note_pjr) {

        try {

            String query = "UPDATE Assister set note_de = ?, note_tp = ?, note_pjr = ? where (matricule_etudiant, code_cours) in ((" + matriculeEtudiant + "," + codeCours + "))";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setDouble(1, note_de);
            preparedStmt.setDouble(2, note_tp);
            preparedStmt.setDouble(3, note_pjr);

            preparedStmt.executeUpdate();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }

    static private boolean creerIdentite(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue) {

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

            return true;

        } catch (SQLException e) { System.out.println(e); return false; }
    }

    static boolean creerEtudiant(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue, String password, int numeroGroupe) {

        try {

            creerIdentite(nom, prenom, sexe, photo, dateNaissance, paysNaissance, villeNaissance, tel, email, ville, codePostal, numeroRue, nomRue);

            String query = "Insert into Etudiant(password, numero_groupe, id_identite) values (?,?,(select id_identite from Identite where tel = " + tel + "))";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, password);
            preparedStmt.setInt(2, numeroGroupe);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }

    static boolean creerProfesseur(String nom, String prenom, char sexe, java.sql.Blob photo, java.sql.Date dateNaissance, String paysNaissance, String villeNaissance, String tel, String email, String ville, String codePostal, String numeroRue, String nomRue, String password) {

        try {

            creerIdentite(nom, prenom, sexe, photo, dateNaissance, paysNaissance, villeNaissance, tel, email, ville, codePostal, numeroRue, nomRue);

            String query = "Insert into Professeur(password, id_identite) values (?,(select id_identite from Identite where tel = " + tel + "))";

            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, password);

            preparedStmt.execute();

        } catch (SQLException e) { System.out.println(e); return false; }

        return true;
    }



    static boolean login(int login, String pass) {

        Utils.Role role = getUserRole(login);

        if (role == null) {
            System.out.println("[ERREUR] Identifiant incorrect.");
            return false;
        }

        String encryptedPass = Utils.encryptPassword(pass);

        try {

            String query;

            if (role == Utils.Role.etudiant) {
                System.out.println("Connection en tant qu'Etudiant...");
                query = "select matricule_etudiant from Etudiant where matricule_etudiant = ? and password =  ?";
            } else if (role == Utils.Role.professeur) {
                System.out.println("Connection en tant que Professeur...");
                query = "select matricule_professeur from Professeur where matricule_professeur = ? and password =  ?";
            } else {
                System.out.println("Connection en tant qu'Administateur...");
                query = "select numero_administrateur from Administrateur where numero_administrateur = ? and password =  ?";
            }

            PreparedStatement stmt = Database.connection.prepareStatement(query);

            stmt.setInt(1, login);
            stmt.setString(2, encryptedPass);

            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("[ERREUR] Mot de passe invalide !");
                return false;
            } else {
                System.out.println("[SUCCES] Connecté avec succes !");
                Main.connectedUser = rs.getInt(0);
                Main.userRole = role;
                return true;
            }
        } catch (SQLException e) { System.out.println(e); return false; }
    }

    private static Utils.Role getUserRole(int number) {

        try {

            Statement stmt = Database.connection.createStatement();

            ResultSet rs = stmt.executeQuery("select matricule_etudiant from Etudiant where matricule_etudiant =" + number);

            if (rs.isBeforeFirst()) {
                return Utils.Role.etudiant;
            }

            rs = stmt.executeQuery("select matricule_professeur from Professeur where matricule_professeur =" + number);

            if (rs.isBeforeFirst()) {
                return Utils.Role.professeur;
            }

            rs = stmt.executeQuery("select numero_administrateur from Administrateur where numero_administrateur =" + number);

            if (rs.isBeforeFirst()) {
                return Utils.Role.admin;
            }

        } catch (SQLException e) { System.out.println(e); return null; }

        return null;
    }


}