package fr.dauphine.sj.monrocqxu.appMedecin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AppMedecinUtil {
	//Variable globale
	public static final String ATT_SESSION_USER = "utilisateur";
	public static final String SESSION_ROLE = "utilisateur_role";
	public static final String ERREUR = "erreur";
	public static final String CHEMIN_ESPACE ="/appMedecin/espace";
	public static final String CHEMIN_CONNEXION ="/appMedecin/connexion";
	public static final String CHEMIN_INSCRIPTION ="/appMedecin/inscription";
	public static final String CHEMIN_AJOUT ="/appMedecin/ajout";
	public static final String CHEMIN_RACINE = "/appMedecin/";

	//Mail
	public static final String MAIL_HOST = "localhost";//Utilisation FAKESMTP
	public static final String MAIL_WEBSITE_ADRESS = "noreply@rdvmedecin.fr";
	public static final String MAIL_USERNAME = "";
	public static final String MAIL_PASSWORD = "";



	//Methode
	public static boolean isAuthenticated (HttpServletRequest request) {
		HttpSession checkSession = request.getSession();
		if (checkSession != null && checkSession.getAttribute(ATT_SESSION_USER) != null) {
			return true;
		}
		return false;
	}

}
