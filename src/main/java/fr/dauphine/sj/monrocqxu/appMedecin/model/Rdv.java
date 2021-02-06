package fr.dauphine.sj.monrocqxu.appMedecin.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rdv")
public class Rdv implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rdv_id", unique = true, nullable = false)
    private int id;
	private int patient_id;
	private int medecin_id;
	private int centre_id;
	private String date;
	private int semaine;
	private boolean actif;
	private String commentaire;
	private int creneau;
	private int specialite_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getMedecin_id() {
		return medecin_id;
	}
	public void setMedecin_id(int medecin_id) {
		this.medecin_id = medecin_id;
	}
	public int getCentre_id() {
		return centre_id;
	}
	public void setCentre_id(int centre_id) {
		this.centre_id = centre_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getCreneau() {
		return creneau;
	}
	public void setCreneau(int creneau) {
		this.creneau = creneau;
	}
	public int getSpecialite_id() {
		return specialite_id;
	}
	public void setSpecialite_id(int specialite_id) {
		this.specialite_id = specialite_id;
	}
	public int getSemaine() {
		return semaine;
	}
	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}
	
}
