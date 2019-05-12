package school_admin;


public class FaitPartie {

    private int numeroGroupe;
    private int matricule;

    public FaitPartie(int numeroGroupe, int matricule) {
        this.numeroGroupe = numeroGroupe;
        this.matricule = matricule;
    }

    public int getNumeroGroupe() {
        return numeroGroupe;
    }

    public void setNumeroGroupe(int numeroGroupe) {
        this.numeroGroupe = numeroGroupe;
    }


    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "FaitPartie{" +
                "numeroGroupe=" + numeroGroupe +
                ", matricule=" + matricule +
                '}';
    }
}
