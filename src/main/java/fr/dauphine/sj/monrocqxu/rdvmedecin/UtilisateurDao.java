package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;

public interface UtilisateurDao {
	
	//List<Utilisateur> getUserDetails();

	String ajouter(Utilisateur utilisateur);

	Utilisateur create(Utilisateur utilisateur);

}

