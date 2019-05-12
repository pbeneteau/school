package school_admin;


import java.util.ArrayList;

public class GroupeEleve {

    private int numeroGroupe;
    private Cours cours;
    private ArrayList<Etudiant> etudiants;

    GroupeEleve(int numeroGroupe, Cours cours) {
        this.numeroGroupe = numeroGroupe;
        this.cours = cours;
    }

    public int getNumeroGroupe() {
        return numeroGroupe;
    }

    public void setNumeroGroupe(int numeroGroupe) {
        this.numeroGroupe = numeroGroupe;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    @Override
    public String toString() {
        return "GroupeEleve {" +
                "numeroGroupe=" + numeroGroupe +
                ", cours: " + cours.toString() +
                '}';
    }
}
