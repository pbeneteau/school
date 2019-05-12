package school_admin;


public class Administrateur {

    private int matricule;
    private String nom;
    private String prenom;
    private String tel;
    private String email;

    Administrateur(int matricule, String nom, String prenom, String tel, String email) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
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
        return "Administrateur{" +
                "matricule=" + matricule +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
