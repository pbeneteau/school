package school_admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static Utils.Role userRole = null;
    public static int connectedUser = 20190000;

    public static void main(String[] args) {


        // Initialisation base de donnee
        boolean success = Database.init();

        if (!success)
            return;

        //Etudiant etudiant;

        //etudiant = Database.getEtudiant(20190001);

        //System.out.println(etudiant.toString());


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
        //Database.ajouterNotesEtudiant(7, 20190000, 14, 15, 10);

        //Database.majNotesEtudiant(5, 2, 4,5,8);


        ////////////////////


        displayHeader();
        /*
        displayHeader();

        login();

        switch (userRole) {
            case etudiant:
                displayEtudiantMenu();
                break;
            case professeur:
                displayProfesseurMenu();
                break;
            case admin:
                displayAdminMenu();
                break;
        }
        */

        displayAdminMenu();
    }


    private static void displayHeader() {

        System.out.println("   _____      _                 _ \n" +
                "  / ____|    | |               | |\n" +
                " | (___   ___| |__   ___   ___ | |\n" +
                "  \\___ \\ / __| '_ \\ / _ \\ / _ \\| |\n" +
                "  ____) | (__| | | | (_) | (_) | |\n" +
                " |_____/ \\___|_| |_|\\___/ \\___/|_|\n" +
                "                                  \n" +
                "                                  ");
        System.out.println("Extranet\n");
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

    private static void displayEtudiantMenu() {

        int choice;

        do {
            Scanner scan = new Scanner(System.in);

            // Display the menu
            System.out.println("1 -\t Consulter le bulletin de notes");
            System.out.println("2 -\t Consulter liste groupes");
            System.out.println("3 -\t Consulter liste cours");
            System.out.println("4 -\t Quitter");


            System.out.print("-> ");

            //Get user's choice
            choice = scan.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Relevé de notes: ");
                    ArrayList<Cours> cours = Database.getCoursEtudiant(connectedUser);

                    if (cours == null) {
                        System.out.println("Cet élève ne suit pas de cours.");
                        break;
                    }
                    System.out.println("Matière \t\t Module \t DE (/20) \t TP (/20) \t Projet (/20)");
                    for (Cours item : cours) {
                        System.out.println(item.getNom() + "\t" + item.getDescription() + "\t" + item.getNoteDE() + "\t" + item.getNoteTP() + "\t" + item.getNoteProjet());
                    }

                    System.out.println("\n");
                    break;

                case 2:
                    displayAllGroupes();

                    System.out.println("\n");
                    break;

                case 3:
                    displayAllCours();

                    System.out.println("\n");
                    break;
                default:
                    System.out.println("Choix invalide");
                    System.out.println("\n");
            }
        } while (choice != 4);
    }


    private static void displayProfesseurMenu() {

        int choice;

        do {

            Scanner in = new Scanner(System.in);
            // Display the menu
            System.out.println("1 -\t Saisir les notes");
            System.out.println("2 -\t Consulter bulletin de notes");
            System.out.println("3 -\t Consulter listes groupe");
            System.out.println("4 -\t Consulter listes cours");
            System.out.println("5 -\t Quitter");

            System.out.print("-> ");

            choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Saisie des notes d'un élève\n");

                    int mat;
                    int cours;
                    int de;
                    int tp;
                    int pjr;

                    Scanner key = new Scanner(System.in);

                    System.out.print("Matricule de l'élève: ");
                    mat = key.nextInt();

                    System.out.print("Code du cours: ");
                    cours = key.nextInt();

                    System.out.print("Note DE: ");
                    de = key.nextInt();

                    System.out.print("Note TP: ");
                    tp = key.nextInt();

                    System.out.print("Note Projet: ");
                    pjr = key.nextInt();

                    if (Database.ajouterNotesEtudiant(cours, mat, de, tp, pjr)) {
                        System.out.println("Les notes ont été ajoutées avec succès !\n");
                    } else {
                        System.out.println("Erreur lors de la saisie des notes");
                    }
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.println("Relevé de notes \n");

                    Scanner key1 = new Scanner(System.in);

                    System.out.print("Matricule de l'élève: ");
                    int mat1 = key1.nextInt();

                    ArrayList<Cours> cours1 = Database.getCoursEtudiant(mat1);

                    if (cours1 == null) {
                        System.out.println("Cet élève ne suit pas de cours.");
                        break;
                    }
                    System.out.println("Matière \t\t Module \t DE (/20) \t TP (/20) \t Projet (/20)");
                    for (Cours item : cours1) {
                        System.out.println(item.getNom() + "\t" + item.getDescription() + "\t" + item.getNoteDE() + "\t" + item.getNoteTP() + "\t" + item.getNoteProjet());
                    }

                    System.out.println("\n");
                    break;

                case 3:
                    displayAllGroupes();

                    System.out.println("\n");
                    break;

                case 4:
                    displayAllCours();

                    System.out.println("\n");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    System.out.println("\n");
            }
        } while (choice != 5);
    }

    private static void displayAdminMenu() {

        int choice;

        do {

            Scanner in = new Scanner(System.in);
            // Display the menu
            System.out.println("1\t Inscrire un eleve et affectation d'un groupe"); // genre ajout eleve , ce fait en meme temps
            System.out.println("2\t Valider bulletin eleve"); // on peut mettre fonction d'avoir le bulletin et rajouter une fonction mais on l'a pas
            System.out.println("3\t Mettre a jour les notes"); // fonctions maj note
            System.out.println("4 -\t Consulter listes groupe");
            System.out.println("5 -\t Consulter listes cours");
            System.out.println("6\t Quitter");

            System.out.println("-> ");

            //Get user's choice
            choice = in.nextInt();

            //Display the title of the chosen module
            switch (choice) {
                case 1:
                    System.out.println("Ajouter un élève \n");

                    String prenom;
                    String nom;
                    String sexe;
                    String pays_n;
                    String ville_n;
                    String tel;
                    String email;
                    String ville;
                    String codePostal;
                    String numeroRue;
                    String nomRue;
                    int groupe;
                    String pass;

                    Scanner key = new Scanner(System.in);

                    System.out.print("Prénom: ");
                    prenom = key.nextLine();

                    System.out.print("Nom: ");
                    nom = key.nextLine();

                    System.out.print("Sexe (M ou F): ");
                    sexe = key.nextLine();

                    System.out.print("Pays de naissance: ");
                    pays_n = key.nextLine();

                    System.out.print("Ville de naissance: ");
                    ville_n = key.nextLine();

                    System.out.print("Tel: ");
                    tel = key.nextLine();

                    System.out.print("Email: ");
                    email = key.nextLine();

                    System.out.print("Ville: ");
                    ville = key.nextLine();

                    System.out.print("Code postal: ");
                    codePostal = key.nextLine();

                    System.out.print("Numero de rue: ");
                    numeroRue = key.nextLine();

                    System.out.print("Nom de rue: ");
                    nomRue = key.nextLine();

                    System.out.print("Numero du groupe: ");
                    groupe = key.nextInt();

                    System.out.print("Mot de passe: ");
                    pass = key.nextLine();

                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                    if (Database.creerEtudiant(nom, prenom, sexe.charAt(0), null, date, pays_n, ville_n, tel, email, ville, codePostal, numeroRue, nomRue, pass, groupe)) {
                        System.out.println("Élève ajouté avec succès !");
                    } else {
                        System.out.println("Erreur lors de la saisie des informations !");
                    }

                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("Relevé de notes \n");

                    Scanner key1 = new Scanner(System.in);

                    System.out.print("Matricule de l'élève: ");
                    int mat1 = key1.nextInt();

                    ArrayList<Cours> cours1 = Database.getCoursEtudiant(mat1);

                    if (cours1 == null) {
                        System.out.println("Cet élève ne suit pas de cours.");
                        break;
                    }
                    System.out.println("Matière \t\t Module \t DE (/20) \t TP (/20) \t Projet (/20)");
                    for (Cours item : cours1) {
                        System.out.println(item.getNom() + "\t" + item.getDescription() + "\t" + item.getNoteDE() + "\t" + item.getNoteTP() + "\t" + item.getNoteProjet());
                    }

                    System.out.print("Valider ce bulletin ? (O/N): ");
                    String validate = key1.nextLine();

                    if (validate == "O") {
                        System.out.print("Ce relevé de notes a été validé avec succès !");
                    } else {
                        System.out.print("Ce relevé de notes n'est pas validé.");
                    }

                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Mise à jour des notes d'un élève\n");

                    int mat;
                    int cours;
                    int de;
                    int tp;
                    int pjr;

                    Scanner key2 = new Scanner(System.in);

                    System.out.print("Matricule de l'élève: ");
                    mat = key2.nextInt();

                    System.out.print("Code du cours: ");
                    cours = key2.nextInt();

                    System.out.print("Note DE: ");
                    de = key2.nextInt();

                    System.out.print("Note TP: ");
                    tp = key2.nextInt();

                    System.out.print("Note Projet: ");
                    pjr = key2.nextInt();

                    if (Database.majNotesEtudiant(mat, cours, de, tp, pjr)) {
                        System.out.println("Les notes ont été mise à jour avec succès !\n");
                    } else {
                        System.out.println("Erreur lors de la mise à jour des notes !");
                    }
                    break;
                case 4:
                    displayAllGroupes();
                    System.out.println("\n");
                    break;
                case 5:
                    displayAllCours();
                    System.out.println("\n");
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choice != 6);
    }

    private static void displayAllGroupes() {

        System.out.println("Liste de tous les groupes: ");

        ArrayList<Integer> groupes = Database.getAllGroupes();

        if (groupes == null) {
            System.out.println("Il n'y a aucun groupe.");
            return;
        }

        for (Integer groupe1 : groupes) {
            System.out.println("Groupe " + groupe1);
        }
    }

    private static void displayAllCours() {

        System.out.println("Liste de tous les cours ");

        ArrayList<Cours> allCours = Database.getAllCours();

        if (allCours == null) {
            System.out.println("Il n'y a aucun cours.");
            return;
        }

        for (Cours item : allCours) {
            System.out.println(item.getNom() + "\t" + item.getDescription() + "\t" + item.getCoefficient() + "\t" + item.getCoefficientDe() + "\t" + item.getCoefficientTp() + "\t" + item.getNoteProjet());
        }
    }


}