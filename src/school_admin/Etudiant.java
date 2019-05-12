package school_admin;


import java.sql.Date;
import java.util.ArrayList;

public class Etudiant {

    private int matricule;
    private String nom;
    private String prenom;
    private String sexe;
    private java.sql.Date dateNaissance;
    private String paysNaissance;
    private String villeNaissance;
    private String photo;
    private String numeroRue;
    private String nomRue;
    private String codePostale;
    private String ville;
    private ArrayList<Cours> cours;
    private String tel;
    private String email;


    Etudiant(int matricule, String nom, String prenom, String sexe, Date dateNaissance, String paysNaissance, String villeNaissance, String photo, String numeroRue, String nomRue, String codePostale, String ville, ArrayList<Cours> cours, String tel, String email) {

        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.paysNaissance = paysNaissance;
        this.villeNaissance = villeNaissance;
        this.photo = photo;
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.codePostale = codePostale;
        this.ville = ville;
        this.cours = cours;
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


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    public java.sql.Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(java.sql.Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }


    public String getPaysNaissance() {
        return paysNaissance;
    }

    public void setPaysNaissance(String paysNaissance) {
        this.paysNaissance = paysNaissance;
    }


    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
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
        return "Etudiant{" +
                "matricule=" + matricule +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", paysNaissance='" + paysNaissance + '\'' +
                ", villeNaissance='" + villeNaissance + '\'' +
                ", photo='" + photo + '\'' +
                ", numeroRue='" + numeroRue + '\'' +
                ", nomRue='" + nomRue + '\'' +
                ", codePostale='" + codePostale + '\'' +
                ", ville='" + ville + '\'' +
                ", cours=" + cours +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
