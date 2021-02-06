package fr.dauphine.sj.monrocqxu.appMedecin.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
						+ utilisateur.getNom() + utilisateur.getPrenom() +" ! <br>"
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
			message.setContent(AppMedecinUtil.ConvertISOtoUTF8(msg),
		             "text/html");

			Transport.send(message);

			System.out.println("Message envoyé avec succés");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
