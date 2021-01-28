package fr.dauphine.sj.monrocqxu.appMedecin.util;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class AppMedecinUtil {
	//Variable globale
	public static final String ATT_SESSION_USER = "utilisateur";
	public static final String SESSION_ROLE = "utilisateur_role";
	public static final String ERREUR = "erreur";
	public static final String CHEMIN_ESPACE ="/appMedecin/espace";
	public static final String CHEMIN_CONNEXION ="/appMedecin/connexion";
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
