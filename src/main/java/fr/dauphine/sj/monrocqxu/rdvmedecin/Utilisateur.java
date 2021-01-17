package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//@Table(name = "personne", uniqueConstraints = {
//        @UniqueConstraint(columnNames = "ID") })

@Table(name="utilisateur")
public class Utilisateur implements Serializable 
{   
    private static final long serialVersionUID = -1798070786993154676L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilisateur_id", unique = true, nullable = false)
    private Integer id;
     
     
    @Column(name = "nom", unique = false, nullable = false, length = 100)
    private String nom;
     
    @Column(name = "prenom", unique = false, nullable = false, length = 100)
    private String prenom;
    
    @Column(name = "mail", unique = true, length = 100)
    private String mail;
    
    @Column(name = "motdepasse", unique = false, nullable = false, length = 100)
    private String mdp;
    
	@Column(name = "role", unique = false, nullable = false, length = 100)
    private String role;
    
	@Column(name = "enabled", unique = false, nullable = false, length = 100)
    private String enabed;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnabed() {
		return enabed;
	}

	public void setEnabed(String enabed) {
		this.enabed = enabed;
	}

 
	
	
    //Getters and setters
    
}