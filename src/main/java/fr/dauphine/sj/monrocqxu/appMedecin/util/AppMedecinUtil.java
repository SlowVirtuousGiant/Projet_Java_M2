package fr.dauphine.sj.monrocqxu.appMedecin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AppMedecinUtil {
	//Variable globale
	public static final String ATT_SESSION_USER = "utilisateur";
	public static final String ERREUR = "erreur";
	public static final String CHEMIN_ESPACE ="/appMedecin/espace";
	public static final String CHEMIN_CONNEXION ="/appMedecin/connexion";
    public static final String CHEMIN_RACINE = "/appMedecin/";
	
	
	
	//Methode
	public static boolean isAuthenticated (HttpServletRequest request) {
		HttpSession checkSession = request.getSession();
		if (checkSession != null && checkSession.getAttribute(ATT_SESSION_USER) != null) {
			return true;
		}
		return false;
		
	}
	

}
