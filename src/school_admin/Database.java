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


        static boolean CreateGroup (int numero_groupe, int numero_cours) {

            try {
                Statement stmt = Database.connection.createStatement();


                String queryg = "Insert into Groupe_eleve ( numero_groupe, numero_cours)"
                        + " values (?, ?)";

                //
                PreparedStatement preparedStmt = connection.prepareStatement(queryg);
                preparedStmt.setInt(1, numero_groupe);
                preparedStmt.setInt(2, numero_cours);

            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
            return true;
        }


    static boolean MajEleveDuGroup (int numero_groupe) {

        try {

            Statement stmt = Database.connection.createStatement();


            String queryg = "UPDATE Etudiant set numero_groupe = ? ";
            PreparedStatement preparedStmt = connection.prepareStatement(queryg);
            preparedStmt.setInt(1, numero_groupe);

            preparedStmt.executeUpdate();
            //

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    static boolean RemoveEleveDuGroup () {

        try {

            Statement stmt = Database.connection.createStatement();


            String queryg = "UPDATE Etudiant set numero_groupe = NULL";
            PreparedStatement preparedStmt = connection.prepareStatement(queryg);

            preparedStmt.executeUpdate();
            //

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }



    static boolean CreateCours(int code, String nom, String description, String date, double coefficient, double coefficentDE, double coefficientTP, double coefficientProjet, int numeroCours, int matriculeProf, int numero_groupe) {


        try {

            Statement stmt = Database.connection.createStatement();


            // Creation d'un nouveau Cours
            String query = " Insert into Cours ( code, nom, description, date, coefficient, coefficient_de, coefficient_tp, coefficient_projet)"
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

            connection.close();
        }
        catch (SQLException e) { System.out.println(e); return false; }
        return true;
    }

            static boolean AssociateCoursGroup(int numeroCours){

                try {

                    Statement stmt = Database.connection.createStatement();
                // Association d'un Cours à un groupe

                String query2 = "Insert into Groupe_eleve(numero_cours)" + "values(?)";


                // je sais pas si c'est util
                ResultSet rs3=stmt.executeQuery("select code from Cours");

                numeroCours = rs3.getInt("code");

                //

                PreparedStatement preparedStmt2 = connection.prepareStatement(query2);
                preparedStmt2.setInt(1,numeroCours);

                    connection.close();
                }
                catch (SQLException e) { System.out.println(e); return false; }
                return true;

            }


    // Association professeur a un cours
    static boolean AssociateProfCours(int code, int matriculeProf) {

        try {

            Statement stmt = Database.connection.createStatement();

        String query3 = "Insert into Enseigne ( matricule, code)"
                + " values (?, ?)";

        // je sais pas si c'est util
        ResultSet rs = stmt.executeQuery("select matricule from Professeur");

        matriculeProf = rs.getInt("matricule");
        //

        PreparedStatement preparedStmt3 = connection.prepareStatement(query3);
        preparedStmt3.setInt(1, matriculeProf);
        preparedStmt3.setInt(2, code);

            connection.close();
        }
        catch (SQLException e) { System.out.println(e); return false; }
        return true;
    }

    // gestion note uniquement par prof du cours
    static boolean saisieNote (double note_de, double note_tp, double note_pjr) {

        try {

            Statement stmt = Database.connection.createStatement();

            String query = "INSERT into Assister (note_de, note_tp, note_pjr)" + "values (?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, note_de);
            preparedStmt.setDouble(2, note_tp);
            preparedStmt.setDouble(3, note_pjr);

            preparedStmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }


    // mise a jour unquement par personne scola sur demande prof
    static boolean MajsaisieNote (double note_de, double note_tp, double note_pjr) {

        try {

            Statement stmt = Database.connection.createStatement();

            String query = "UPDATE Assister set note_de = ?, note_tp = ? , note_pjr = ? ";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, note_de);
            preparedStmt.setDouble(2, note_tp);
            preparedStmt.setDouble(3, note_pjr);

            preparedStmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }



        static boolean releverNote (){

            try {

                Statement stmt = Database.connection.createStatement();

              String query = "select (note_de, note_tp, note_pjr) from Assister inner join Etudiant E on Assister.matricule = E.matricule ";
                PreparedStatement preparedStmt = connection.prepareStatement(query);

                preparedStmt.execute();
                
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
            return true;
        }


}