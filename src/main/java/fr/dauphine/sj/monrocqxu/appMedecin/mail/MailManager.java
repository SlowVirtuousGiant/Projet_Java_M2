package fr.dauphine.sj.monrocqxu.appMedecin.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Creneau;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.MAIL_HOST;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.MAIL_WEBSITE_ADRESS;

public class MailManager {

	private static Session session;

	public MailManager() {
		Properties props = new Properties();
		props.put("mail.smtp.host", MAIL_HOST);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "false");
		session = Session.getInstance(props);
		//props.put("mail.smtp.port", "");//changer la config du port ici
		//CreateSession(props);
	}

	/*
	private void CreateSession(Properties props) {//Si on devait utiliser un mot de passe:
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_USERNAME, MAIL_PASSWORD);
			}
		});
	}
	 */

	public void sendTestMessage(String destination) {
		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(MAIL_WEBSITE_ADRESS));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));

			message.setSubject("Bienvenue sur rdvmedecin.fr");

			message.setContent("<p> Bonjour <strong>vive les citrouilles</strong> "
					+ "<img src='https://www.fraicheurquebec.com/images/fraicheur-products/main/Citrouille.png' alt='img' /> ",
					"text/html");

			Transport.send(message);

			System.out.println("Message envoyé avec succés");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void envoiInscriptionMail(Utilisateur utilisateur,String mdp) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_WEBSITE_ADRESS));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utilisateur.getMail()));
			message.setSubject("Bienvenue sur rdvmedecin.fr");
			String sexe;
			if(utilisateur.getSexe().equals("homme")) {
				sexe = "Mr. ";
			}else {
				sexe = "Mme. ";
			}
			String msg = null;
			if(utilisateur.getRole().equals("PATIENT")) {
				msg = "Bonjour,<br>"
						+ "Bienvenue sur le site RDVmedecin " 
						+ sexe
						+ utilisateur.getNom() + " " + utilisateur.getPrenom() +" ! <br>"
						+ "Merci de vous êtes inscrit sur notre site, voici vos coordonnées de connexion : <br>"
						+ "Mail : " + utilisateur.getMail() + "<br>"
						+ "Mot de passe : " + mdp + "<br>"
						+ "À très bientôt sur notre site, de la part de toute l'équipe de RDVmedecin ! ";
			}else {
				msg = "Bonjour,<br>"
						+ "Bienvenue sur le site RDVmedecin Dr. "
						+ utilisateur.getNom() + utilisateur.getPrenom() +" ! <br>"
						+ "Merci de vous êtes inscrit sur notre site, voici vos coordonnées de connexion : <br>"
						+ "Mail : " + utilisateur.getMail() + "<br>"
						+ "Mot de passe : " + mdp + "<br>"
						+ "Veuillez vous connecter pour  personnaliser votre mot de passe <br>"
						+ "À très bientôt sur notre site, de la part de toute l'équipe de RDVmedecin ! ";
			}
			message.setContent((msg),
					"text/html; charset=UTF-8");
			Transport.send(message);
			System.out.println("Message envoyé avec succés");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void envoiMailDesactivationCompte(Utilisateur utilisateur, List<Rdv> listRdv) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_WEBSITE_ADRESS));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utilisateur.getMail()));
			message.setHeader("toto", "UTF-8");
			message.setSubject("Confirmation de désactivation du compte sur RDVmedecin.fr");
			String sexe;
			if(utilisateur.getSexe().equals("homme")) {
				sexe = "Mr. ";
			}else {
				sexe = "Mme. ";
			}
			String msg = null;
			String msgRDVcanceled = "";

			if(utilisateur.getRole().equals("PATIENT")) {
				for(Rdv rdv:listRdv) {


					Utilisateur medecin = UtilisateurDao .getUtilisateurByID(rdv.getMedecin_id());
					Centre centre = CentreDao.getCentreByID(rdv.getCentre_id());
					Specialite specialite = SpecialiteDao.getSpecialiteByID(rdv.getSpecialite_id());
					Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
					msgRDVcanceled = msgRDVcanceled + "Date : " + rdv.getDate()+"<br>"
							+ "Heure : " + c.getName()+"<br>"
							+ "Dr."+medecin.getNom() + " " + medecin.getPrenom()+"<br>"
							+ "Spécialité : "+specialite.getSpecialite()+"<br>"
							+ "Centre médical : "+centre.getNom()+"<br>"
							+ "Adresse du centre médical : "+centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()
							+"<br>"
							+ "Telephone du centre médical : "+centre.getTelephone()+"<br>"
							+"___________________________________________________<br>";
				}
				msg = "Bonjour "+ sexe + utilisateur.getNom() + " " + utilisateur.getPrenom() + ",<br>"
						+ "Nous vous souhaitons une bonne continuation et confirmons la désactivation de votre compte ! <br>" 
						+ "Voici les rendez-vous qui ont été annulés suite à votre désactivation : <br>"
						+ msgRDVcanceled
						+ "<br>Merci pour votre visite ! ";
			}else {
				msg = "Bonjour "+ sexe + utilisateur.getNom() + " " + utilisateur.getPrenom() + ",<br>"
						+ "Nous vous souhaitons une bonne continuation et confirmons la désactivation de votre compte ! " 
						+ "Merci cependant de votre visite.<br>";
			}
			message.setContent((msg),
					"text/html; charset=UTF-8");
			Transport.send(message);
			System.out.println("Message envoyé avec succés");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void envoiRDVDetail(Utilisateur utilisateur, Rdv rdv) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_WEBSITE_ADRESS));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utilisateur.getMail()));
			message.setHeader("toto", "UTF-8");
			Boolean statut = rdv.isActif();
			String keyword ="";
			if(statut) {
				keyword = "la prise";
			}else {
				keyword = "l'annulation";
			}
			message.setSubject("Confirmation de "+keyword+" de RDV sur RDVmedecin.fr");
			String sexe;
			if(utilisateur.getSexe().equals("homme")) {
				sexe = "Mr. ";
			}else {
				sexe = "Mme. ";
			}
			String msg = null;
			String detailRDV = "";

			Utilisateur medecin = UtilisateurDao .getUtilisateurByID(rdv.getMedecin_id());
			Centre centre = CentreDao.getCentreByID(rdv.getCentre_id());
			Specialite specialite = SpecialiteDao.getSpecialiteByID(rdv.getSpecialite_id());
			Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
			
			
			detailRDV =  "<br>"+"Date : " + rdv.getDate()+"<br>"
					+ "Heure : " + c.getName()+"<br>"
					+ "Dr."+medecin.getNom() + " " + medecin.getPrenom()+"<br>"
					+ "Spécialité : "+specialite.getSpecialite()+"<br>"
					+ "Centre médical : "+centre.getNom()+"<br>"
					+ "Adresse du centre médical : "+centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()
					+"<br>"
					+ "Telephone du centre médical : "+centre.getTelephone()+"<br>"
					+"___________________________________________________<br>";

			msg = "Bonjour "+ sexe + utilisateur.getNom() + " " + utilisateur.getPrenom() + ",<br>"
					+ "Nous vous confirmons " + keyword + " du rendez-vous suivant : " 
					+ detailRDV
					+ "<br>À bientôt ! ";
			message.setContent((msg),
					"text/html; charset=UTF-8");
			Transport.send(message);
			System.out.println("Message envoyé avec succés");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void envoieRDVRappel(Rdv rdv) {
		try {
			Utilisateur utilisateur = UtilisateurDao.getUtilisateurByID(rdv.getPatient_id());
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_WEBSITE_ADRESS));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utilisateur.getMail()));
			message.setHeader("toto", "UTF-8");
			message.setSubject("Rappel de votre RDV sur RDVmedecin.fr à venir"+rdv.getDate());
			String sexe;
			if(utilisateur.getSexe().equals("homme")) {
				sexe = "Mr. ";
			}else {
				sexe = "Mme. ";
			}
			String msg = null;
			String detailRDV = "";

			Utilisateur medecin = UtilisateurDao .getUtilisateurByID(rdv.getMedecin_id());
			Centre centre = CentreDao.getCentreByID(rdv.getCentre_id());
			Specialite specialite = SpecialiteDao.getSpecialiteByID(rdv.getSpecialite_id());
			Creneau c = Creneau.valeurIdCreneau(rdv.getCreneau());
			
			
			detailRDV =  "<br>"+"Date : " + rdv.getDate()+"<br>"
					+ "Heure : " + c.getName()+"<br>"
					+ "Dr."+medecin.getNom() + " " + medecin.getPrenom()+"<br>"
					+ "Spécialité : "+specialite.getSpecialite()+"<br>"
					+ "Centre médical : "+centre.getNom()+"<br>"
					+ "Adresse du centre médical : "+centre.getAdresse() + " " + centre.getVille() + " " + centre.getCode_postal()
					+"<br>"
					+ "Telephone du centre médical : "+centre.getTelephone()+"<br>"
					+"___________________________________________________<br>";

			msg = "Bonjour "+ sexe + utilisateur.getNom() + " " + utilisateur.getPrenom() + ",<br>"
					+ "Nous vous rappelons votre rendez-vous suivant : " 
					+ detailRDV
					+ "<br>À bientôt ! ";
			message.setContent((msg),
					"text/html; charset=UTF-8");
			Transport.send(message);
			System.out.println("Message envoyé avec succés");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


}
