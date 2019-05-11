package school_admin;

import com.mysql.cj.jdbc.SuspendableXAConnection;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Main {

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
        //Database.creerEtudiant("Pierre", "Henri", 'M', null, date, "France", "Paris", "0773672933", "pierrehenri@school.net", "Paris", "750110", "4", "rue Jean Paul Gaultier", "fvajsfsakf", 0);

        //Database.majGroupeEtudiant(5, 4);

        //System.out.println(Database.getMatriculeEtudiantsGroupe(4).toString());

        //Database.creerCours("Optimisation", "Mathématiques", date, 2, 0.7, 0.3, 0, 1);

        //Database.ajouterNotesEtudiant(3, 6, 14, 15, 10);
        //Database.ajouterNotesEtudiant(4, 6, 14, 15, 10);

        Database.majNotesEtudiant(5, 2, 4,5,8);

    }



}