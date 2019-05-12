package school_admin;

public class PersonneResponsable {

    private int num;
    private String prenom;
    private String nom;
    private String email;
    private String tel;
    private String numeroRue;
    private String nomRue;
    private String ville;
    private String pays;
    private String codePostal;

    public PersonneResponsable(int num, String prenom, String nom, String email, String tel, String numeroRue, String nomRue, String ville, String pays, String codePostal) {
        this.num = num;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.tel = tel;
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.ville = ville;
        this.pays = pays;
        this.codePostal = codePostal;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }


    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "PersonneResponsable{" +
                "num=" + num +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", numeroRue='" + numeroRue + '\'' +
                ", nomRue='" + nomRue + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }
}