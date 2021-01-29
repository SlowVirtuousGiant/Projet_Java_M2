package fr.dauphine.sj.monrocqxu.appMedecin.model.rdv;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

@Remote
public interface UtilisateurRdvSessionBeanRemote {
	
	void ajouterRdv(Utilisateur utilisateur);
	List<Utilisateur> getRdv();
}
