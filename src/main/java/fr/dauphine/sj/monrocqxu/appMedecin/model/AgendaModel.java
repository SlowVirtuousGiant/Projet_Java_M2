package fr.dauphine.sj.monrocqxu.appMedecin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class AgendaModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agenda_id", unique = true, nullable = false)
	private Integer id;
	private int affectation_id;
	private int semaine;
	public int getAffectation_id() {
		return affectation_id;
	}
	public void setAffectation_id(int affectation_id) {
		this.affectation_id = affectation_id;
	}
	public int getSemaine() {
		return semaine;
	}
	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}
	public Integer getId() {
		return id;
	}
	
	

	
	
}
