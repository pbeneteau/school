package school_admin;


public class Professeur {

  private int matricule;
  private String nom;
  private String prenom;
  private String numeroRue;
  private String nomRue;
  private String codePostal;
  private String ville;
  private String tel;
  private String email;

  Professeur(int matricule, String nom, String prenom, String numeroRue, String nomRue, String codePostal, String ville, String tel, String email) {
    this.matricule = matricule;
    this.nom = nom;
    this.prenom = prenom;
    this.numeroRue = numeroRue;
    this.nomRue = nomRue;
    this.codePostal = codePostal;
    this.ville = ville;
    this.tel = tel;
    this.email = email;
  }

  public int getMatricule() {
    return matricule;
  }

  public void setMatricule(int matricule) {
    this.matricule = matricule;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }


  public String getNumeroRue() {
    return numeroRue;
  }

  public void setNumeroRue(String numeroRue) {
    this.numeroRue = numeroRue;
  }


  public String getNomRue() {
    return nomRue;
  }

  public void setNomRue(String nomRue) {
    this.nomRue = nomRue;
  }


  public String getCodePostale() {
    return codePostal;
  }

  public void setCodePostale(String codePostale) {
    this.codePostal = codePostale;
  }


  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Professeur{" +
            "matricule=" + matricule +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", numeroRue='" + numeroRue + '\'' +
            ", nomRue='" + nomRue + '\'' +
            ", codePostal='" + codePostal + '\'' +
            ", ville='" + ville + '\'' +
            ", tel='" + tel + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
