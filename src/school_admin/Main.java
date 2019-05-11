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

      //  System.out.println(Database.getMatriculeEtudiantsPromotion("L1").toString());

        //Database.creerCours("Optimisation", "Mathématiques", date, 2, 0.7, 0.3, 0, 20110001);
        //Database.creerCours("Théorie des Groupes", "Mathématiques", date, 2, 0.7, 0.3, 0, 20110002);
        //Database.creerCours("Théorie des Graphes", "Mathématiques", date, 2, 0.5, 0.2, 0.2, 20110003);

        //Database.ajouterNotesEtudiant(3, 6, 14, 15, 10);
        //Database.ajouterNotesEtudiant(4, 6, 14, 15, 10);

        //Database.majNotesEtudiant(5, 2, 4,5,8);


        //displayHeader();

       // login();

       // displayEtudiantMenu();
       // displayProfesseurMenu();
       // displayAdminMenu();
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

   private static void displayEtudiantMenu (){
        Scanner scan = new Scanner(System.in);
        // Display the menu
        System.out.println("1\t Consulter le bulletin de notes");
        System.out.println("2\t Consulter liste groupes et cours"); // incomprehensible la phrase ds le sujet
        System.out.println("3\t Quitter");                              // Je pense ca veut dire qu'on affiche les groupes avec tt les cours


        System.out.println("Please enter your choice:");

        //Get user's choice
        int choice=scan.nextInt();


        switch (choice) {
            // A modifier psk on veut le bulletin de note
            case 1: Database.getCoursEtudiant(20190000);
            System.out.println(Database.getCoursEtudiant(20190000));
            System.out.println("\n");
            displayEtudiantMenu();
                break;
            case 2:
                System.out.println(" fonction liste groupe et cours ");

                System.out.println("\n");
                displayEtudiantMenu();
                break;

            case 3: break;
            default: System.out.println("Invalid choice");

        }
    }


    private static void displayProfesseurMenu (){
        Scanner in = new Scanner(System.in);
        // Display the menu
        System.out.println("1\t Saisir les notes");
        System.out.println("2\t Consulter bulletin de notes");
        System.out.println("3\t Consulter listes groupe et cours ");
        System.out.println("4\t Quitter");

        System.out.println("Please enter your choice:");

        //Get user's choice
        int choice=in.nextInt();

        //Display the title of the chosen module
        switch (choice) {
            case 1:
                // ca chie
              //  Database.majNotesEtudiant(20190001,5,6,9);
            //    System.out.println(Database.majNotesEtudiant(20190001,5,6,9));
                System.out.println(" fonction saisie notes");

            System.out.println("\n");
            displayProfesseurMenu();
                break;
            case 2:
                // a modifier
                Database.getCoursEtudiant(20190000);
                System.out.println(Database.getCoursEtudiant(20190000));


            System.out.println("\n");
            displayProfesseurMenu();;
                break;
            case 3:
                // on a pas la fonction
                System.out.println(" fonction liste groupe ");

                System.out.println("\n");
                displayProfesseurMenu();;
                break;

            case 4:
                break;
            default: System.out.println("Invalid choice");

        }
    }

      private static void displayAdminMenu (){
        Scanner in = new Scanner(System.in);
        // Display the menu
        System.out.println("1\t Inscrire un eleve et affectation d'un groupe"); // genre ajout eleve , ce fait en meme temps
        System.out.println("2\t Valider bulletin eleve"); // on peut mettre fonction d'avoir le bulletin et rajouter une fonction mais on l'a pas
        System.out.println("3\t Mettre a jour les notes"); // fonctions maj note
        System.out.println("4\t Consulter listes groupe et cours");
          System.out.println("4\t Quitter");

        System.out.println("Please enter your choice:");

        //Get user's choice
        int choice=in.nextInt();

        //Display the title of the chosen module
        switch (choice) {
            case 1:
                // mettre les parametres ds ()
             //   Database.creerEtudiant();
              //  System.out.println(Database.creerEtudiant());


                System.out.println("\n");
                displayAdminMenu();
                break;
            case 2:
                // a modifier
                Database.getCoursEtudiant(20190000);
                System.out.println(Database.getCoursEtudiant(20190000));


                System.out.println("\n");
                displayAdminMenu();
                break;
            case 3:
                // fonction pu je crois
              //  Database.majNotesEtudiant();
               // System.out.println(Database.majNotesEtudiant());

                System.out.println("\n");
                displayAdminMenu();
                break;
            case 4:
                // on a pas la fonction
                System.out.println(" fonction liste groupe ");

                System.out.println("\n");
                displayAdminMenu();
                break;
            case 5:
            break;
            default: System.out.println("Invalid choice");

        }
    }


}