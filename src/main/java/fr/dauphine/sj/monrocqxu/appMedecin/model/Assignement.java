package fr.dauphine.sj.monrocqxu.appMedecin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assignement")
public class Assignement implements Serializable{
	private static final long serialVersionUID = 1L;

 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignement_id", unique = true, nullable = false)
    private int id;
 	private int medecin_id;
 	private int centre_id;
 	private int specialite_id;
 	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getSpecialite_id() {
		return specialite_id;
	}
	public void setSpecialite_id(int specialite_id) {
		this.specialite_id = specialite_id;
	}
	
	
 	
 	
}
