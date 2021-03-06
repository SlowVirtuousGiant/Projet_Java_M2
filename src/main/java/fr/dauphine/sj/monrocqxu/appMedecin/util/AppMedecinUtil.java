package fr.dauphine.sj.monrocqxu.appMedecin.util;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AppMedecinUtil {
	//Variable globale
	public static final String ATT_SESSION_USER = "utilisateur";
	public static final String ATT_SESSION_SPECIALITES = "specialites";
	public static final String ATT_SESSION_CENTRES = "centres";
	public static final String ATT_SESSION_RDV = "rdv";
	public static final String SESSION_ROLE = "utilisateur_role";
	public static final String ERREUR = "erreur";
	public static final String SUCCES = "succes";
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
	public static final String CHEMIN_CONF_RDV = "/appMedecin/confirmationrdv";
	
	
	//Mail
	public static final String MAIL_HOST = "localhost";//Utilisation FAKESMTP
	public static final String MAIL_WEBSITE_ADRESS = "noreply@rdvmedecin.fr";
	public static final String MAIL_USERNAME = "";
	public static final String MAIL_PASSWORD = "";



	//Methode
	public static boolean isAuthenticated (HttpServletRequest request) {//Fonction pour verifier l'authentification
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
		if ( naissance >=1920 && naissance <= 2021) {
				return true;
			}
		return false;
	}
	public static boolean validationAlphaNum (String txt) {
		return(txt.matches("[A-Za-z0-9]+"));
	}
	public static boolean validationTel (String telephone) {
		Pattern regexFrancais = Pattern.compile("^(33|0)(6|7|9)\\d{8}$");
		Matcher matcher = regexFrancais.matcher(telephone);
		return(matcher.matches());
	}
	
	
	public static String ConvertISOtoUTF8(String iso) {//Permet de convertir les String ISO en UTF-8, utilise dans les JSP
	String item = iso; 

	byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
	return item = new String(bytes, StandardCharsets.UTF_8);
	}
}
