package school_admin;


public class GroupeEleve {

  private int numeroGroupe;

  public GroupeEleve(int numeroGroupe) {
    this.numeroGroupe = numeroGroupe;
  }

  public int getNumeroGroupe() {
    return numeroGroupe;
  }

  public void setNumeroGroupe(int numeroGroupe) {
    this.numeroGroupe = numeroGroupe;
  }

  @Override
  public String toString() {
    return "GroupeEleve{" +
            "numeroGroupe=" + numeroGroupe +
            '}';
  }
}
