package school_admin;

import com.mysql.cj.jdbc.SuspendableXAConnection;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        // Initialisation base de donnee
        boolean success = Database.init();

        if (!success)
            return;

        Etudiant etudiant;

        etudiant = Database.getEtudiant(20190001);

        System.out.println(etudiant.toString());


        DBM.init();
        DBP.init();

    }




}