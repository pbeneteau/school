package school_admin;


import java.sql.Date;

public class Cours {

  private int code;
  private String nom;
  private String description;
  private java.sql.Date date;
  private double coefficient;
  private double coefficientDe;
  private double coefficientTp;
  private double coefficientProjet;
  private double noteDE;
  private double noteTP;
  private double noteProjet;

  public Cours(int code, String nom, String description, Date date, double coefficient, double coefficientDe, double coefficientTp, double coefficientProjet, double noteDE, double noteTP, double noteProjet) {
    this.code = code;
    this.nom = nom;
    this.description = description;
    this.date = date;
    this.coefficient = coefficient;
    this.coefficientDe = coefficientDe;
    this.coefficientTp = coefficientTp;
    this.coefficientProjet = coefficientProjet;
    this.noteDE = noteDE;
    this.noteTP = noteTP;
    this.noteProjet = noteProjet;
  }

  Cours(int code, String nom, String description, Date date, double coefficient, double coefficientDe, double coefficientTp, double coefficientProjet) {
    this.code = code;
    this.nom = nom;
    this.description = description;
    this.date = date;
    this.coefficient = coefficient;
    this.coefficientDe = coefficientDe;
    this.coefficientTp = coefficientTp;
    this.coefficientProjet = coefficientProjet;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public double getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(double coefficient) {
    this.coefficient = coefficient;
  }


  public double getCoefficientDe() {
    return coefficientDe;
  }

  public void setCoefficientDe(double coefficientDe) {
    this.coefficientDe = coefficientDe;
  }


  public double getCoefficientTp() {
    return coefficientTp;
  }

  public void setCoefficientTp(double coefficientTp) {
    this.coefficientTp = coefficientTp;
  }


  public double getCoefficientProjet() {
    return coefficientProjet;
  }

  public void setCoefficientProjet(double coefficientProjet) {
    this.coefficientProjet = coefficientProjet;
  }

  @Override
  public String toString() {
    return "Cours{" +
            "code=" + code +
            ", nom='" + nom + '\'' +
            ", description='" + description + '\'' +
            ", date=" + date +
            ", coefficient=" + coefficient +
            ", coefficientDe=" + coefficientDe +
            ", coefficientTp=" + coefficientTp +
            ", coefficientProjet=" + coefficientProjet +
            ", noteDE=" + noteDE +
            ", noteTP=" + noteTP +
            ", noteProjet=" + noteProjet +
            '}';
  }
}
