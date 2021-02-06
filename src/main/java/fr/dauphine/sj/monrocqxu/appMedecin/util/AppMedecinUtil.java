package fr.dauphine.sj.monrocqxu.appMedecin.util;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AppMedecinUtil {
	//Variable globale
	public static final String ATT_SESSION_USER = "utilisateur";
	public static final String ATT_SESSION_SPECIALITES = "specialites";
	public static final String ATT_SESSION_CENTRES = "centres";
	public static final String SESSION_ROLE = "utilisateur_role";
	public static final String ERREUR = "erreur";
	public static final String CHEMIN_ESPACE ="/appMedecin/espace";
	public static final String CHEMIN_CONNEXION ="/appMedecin/connexion";
	public static final String CHEMIN_DECONNEXION ="/appMedecin/deconnexion";
	public static final String CHEMIN_INSCRIPTION ="/appMedecin/inscription";
	public static final String CHEMIN_AJOUT ="/appMedecin/ajout";
	public static final String CHEMIN_RESERVATION ="/appMedecin/reservation";
	public static final String CHEMIN_RACINE = "/appMedecin/";
	public static final String CHEMIN_PROFIL = "/appMedecin/profil";
	public static final String CHEMIN_MODIFICATION = "/appMedecin/modification";
	public static final String CHEMIN_RESERVATIONDE = "/appMedecin/reservationdetails";
	public static final String CHEMIN_VISU_RDV = "/appMedecin/visualisationrdv";
	
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

	public static boolean validationEmail( String email ) {
		if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			return false;
		}
		return true;
	}

	public static boolean validationMotDePasse( String motDePasse ) {
		if ( motDePasse != null ) {
			if ( motDePasse.length() < 3 ) {
				return false;
			}
		}
		return true;
	}
	public static boolean validationAnneeNaiss( int naissance ) {
		if ( naissance <=1920 ) {
				return false;
			}
		
		return true;
	}
	
	
	public static String ConvertISOtoUTF8(String iso) {
	String item = iso; 

	byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
	return item = new String(bytes, StandardCharsets.UTF_8);
	}
}
