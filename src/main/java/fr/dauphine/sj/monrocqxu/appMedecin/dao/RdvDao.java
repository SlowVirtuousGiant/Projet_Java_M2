package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class RdvDao {

	public List<Rdv> getUtilisateurRdv(Utilisateur utilisateur){
		return getUtilisateurRdv(utilisateur.getId());
	}
	
	public List<Rdv> getUtilisateurRdv(int utilitateur_id){
		throw new NotYetImplementedException();
	}
	
	public void ajouterRdv(Utilisateur utilisateur) {
		
	}

	public void supprimerRdv(Utilisateur utilisateur) {
		
	}
	
}
