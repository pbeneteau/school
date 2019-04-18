package school_admin;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        // Initialisation base de donnee
        boolean success = Database.init();

        if (!success)
            return;

        System.out.println("Fetching data...");

        // Create an ArrayList admins
        ArrayList<Administrateur> administrateurs = Database.getAdmins();
        if (administrateurs != null)
            System.out.println("Admins: OK");
        else
            System.out.println("Admins: Erreur");

        // Create an ArrayList etudiants
        ArrayList<Etudiant> etudiants = Database.getEtudiant();
        if (etudiants != null)
            System.out.println("Etudiant: OK");
        else
            System.out.println("Etudiant: Erreur");

        // Create an ArrayList cours
        ArrayList<Cours> cours = Database.getCours();
        if (cours != null)
            System.out.println("Cours: OK");
        else
            System.out.println("Cours: Erreur");

        // Create an ArrayList professeurs
        ArrayList<Professeur> professeurs = Database.getProfesseurs();
        if (professeurs != null)
            System.out.println("Professeurs: OK");
        else
            System.out.println("Professeurs: Erreur");

        System.out.println("Done. bande de putes");
    }




}