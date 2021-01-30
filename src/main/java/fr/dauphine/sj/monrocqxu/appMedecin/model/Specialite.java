package fr.dauphine.sj.monrocqxu.appMedecin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialite")
public class Specialite {

	@Id
    @Column(name = "specialite_id", unique = true, nullable = false)
    private int id;
	private String specialite;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
}
