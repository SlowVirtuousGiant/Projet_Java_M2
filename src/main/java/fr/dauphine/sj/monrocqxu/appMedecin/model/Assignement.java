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
 	private String specialite;
	
}
