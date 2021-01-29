package fr.dauphine.sj.monrocqxu.appMedecin.model.rdv;

import java.util.List;

import javax.ejb.Stateless;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

@Stateless
public class UtilisateurRdvSessionBean implements UtilisateurRdvSessionBeanRemote {

	@Override
	public void ajouterRdv(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utilisateur> getRdv() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
