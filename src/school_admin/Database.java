package school_admin;

import java.sql.*;
import java.util.ArrayList;

class Database {

    private static Connection connection;

    static boolean init() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://34.76.183.241:3306/bdd?useSSL=false", "root", "qwertyuiop");
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

                int matricule = rs.getInt("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String tel = rs.getString("tel");

                administrateurs.add(new Administrateur(matricule, nom, prenom, tel, email));
            }
        } catch (SQLException e) { System.out.println(e); return null; }

        return administrateurs;
    }

    static ArrayList<Etudiant> getEtudiant() {

        ArrayList<Etudiant> etudiants = new ArrayList<>(); // Create an ArrayList admins

        try {

            Statement stmt= Database.connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Etudiant");

            while(rs.next()) {

                int matricule = rs.getInt("matricule");
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
                int numeroGroupe = rs.getInt("numero_groupe");
                GroupeEleve groupe = getGroup(numeroGroupe);

                etudiants.add(new Etudiant(matricule, nom, prenom, sexe, date_naissance, paysNaissance, villeNaissance, photo, numeroRue, nomRue, codePostale, ville, groupe));
            }
        } catch (SQLException e) { System.out.println(e); return null; }

        return etudiants;
    }

    static GroupeEleve getGroup(int numeroGroupe) {

        GroupeEleve groupe;

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Groupe_eleve where " + numeroGroupe + "= numero_groupe");

            rs.first();
            Cours cours = getCourse(rs.getInt("numero_cours"));
            groupe = new GroupeEleve(numeroGroupe, cours);

        } catch (SQLException e) { System.out.println(e); return null;}

        return groupe;
    }

    static Cours getCourse(int numeroCours) {

        Cours cours;

        try {

            Statement stmt = Database.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cours where " + numeroCours + "= code");

            rs.first();
            int code = rs.getInt("code");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            java.sql.Date date = rs.getDate("date");
            double coefficient = rs.getFloat("coefficient");
            double coefficientDE = rs.getFloat("coefficient_de");
            double coefficientTP = rs.getFloat("coefficient_tp");
            double coefficientProjet = rs.getFloat("coefficient_projet");

            cours = new Cours(code, nom, description, date, coefficient, coefficientDE, coefficientTP, coefficientProjet);

        } catch (SQLException e) { System.out.println(e); return null; }

        return cours;
    }

    static ArrayList<Cours> getCours() {

        ArrayList<Cours> cours = new ArrayList<>(); // Create an ArrayList admins

        try {

            Statement stmt= Database.connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Cours");

            while(rs.next()) {

                int code = rs.getInt("code");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                java.sql.Date date = rs.getDate("date");
                float coefficient = rs.getFloat("coefficient");
                float coefficientDE = rs.getFloat("coefficient_de");
                float coefficientTP = rs.getFloat("coefficient_tp");
                float coefficientProjet = rs.getFloat("coefficient_projet");

                cours.add(new Cours(code, nom, description, date, coefficient, coefficientDE, coefficientTP, coefficientProjet));
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

                int matricule = rs.getInt("matricule");
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

                int num = rs.getInt("num");
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





}