package school_admin;

import com.mysql.cj.jdbc.SuspendableXAConnection;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static Utils.Role userRole = null;
    public static int connectedUser;

    public static void main(String[] args) {


        // Initialisation base de donnee
        boolean success = Database.init();

        if (!success)
            return;

        //Etudiant etudiant;

        //etudiant = Database.getEtudiant(20190001);

        //System.out.println(etudiant.toString());


        DBM.init();
        DBP.init();

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        //DBP.creerCours("Mathématiques", "Théorie des Graphes", date, 2, 0.6, 0.2, 0.2, 1);

        //Database.creerEtudiant("David", "Jean", 'M', null, date, "France", "Paris", "0648303941", "jeandavid@school.net", "Paris", "75013", "13", "Jean Jaures", "fvajsfsakf", 0);
        //Database.creerEtudiant("Lambo", "Marie", 'F', null, date, "France", "Lyon", "0674930683", "marielambo@school.net", "Paris", "75001", "7", "Daguerre", "safgisfhg", 1);

        //Database.majGroupeEtudiant(5, 4);

        System.out.println(Database.getMatriculeEtudiantsPromotion("L1").toString());

        //Database.creerCours("Optimisation", "Mathématiques", date, 2, 0.7, 0.3, 0, 20110001);
        //Database.creerCours("Théorie des Groupes", "Mathématiques", date, 2, 0.7, 0.3, 0, 20110002);
        //Database.creerCours("Théorie des Graphes", "Mathématiques", date, 2, 0.5, 0.2, 0.2, 20110003);

        //Database.ajouterNotesEtudiant(3, 6, 14, 15, 10);
        //Database.ajouterNotesEtudiant(4, 6, 14, 15, 10);

        //Database.majNotesEtudiant(5, 2, 4,5,8);


        displayHeader();

        login();
    }


    private static void displayHeader() {

        System.out.println("School");
        System.out.println("Admin Panel\n");
    }


    // Prof => login: 20110000, pass: dfghjbvcfgz
    private static void login() {

        System.out.println("Connection à l'extranet\n");

        do {
            int login;
            String password;

            Scanner key = new Scanner(System.in);

            System.out.print("\nIdentifiant: ");
            login = key.nextInt();

            key.nextLine();


            System.out.print("Mot de passe: ");
            password = key.nextLine();

            if (Database.login(login, password)) {
                System.out.println("Matricule: " + connectedUser);
            }

        } while (userRole == null);
    }



}