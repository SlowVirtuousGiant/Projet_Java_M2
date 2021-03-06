package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.SUCCES;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.validationAnneeNaiss;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.validationAlphaNum;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.validationTel;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();
	private ArrayList<String> succes = new ArrayList<String>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			response.sendRedirect(CHEMIN_ESPACE);
		} else {
			this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur utilisateur = new Utilisateur();
		if (!UtilisateurDao.isPresent(request.getParameter("mail"))) {
			utilisateur.setNom(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("nom")));
			utilisateur.setPrenom(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("prenom")));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setAdresse(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("adresse")));
			utilisateur.setMail(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("mail")));
			utilisateur.setNaissance(Integer.parseInt(request.getParameter("naissance")));
			utilisateur.setSexe(request.getParameter("sexe"));
			utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
			utilisateur.setVille(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("ville")));
			utilisateur.setRole("PATIENT");
			String mdp = request.getParameter("motdepasse");
			utilisateur.setMotdepasse(BCrypt.hashpw(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("motdepasse")),
					BCrypt.gensalt(12)));
			utilisateur.setActif(true);
			if (validationAlphaNum(utilisateur.getNom()) && validationAlphaNum(utilisateur.getPrenom())) {
				if (validationAnneeNaiss(utilisateur.getNaissance())) {
					if(validationTel(utilisateur.getTelephone())) {
						if (UtilisateurDao.ajouter(utilisateur)) {
							try {
								MailManager.envoiInscriptionMail(utilisateur, mdp);
							} catch (Exception e) {
								e.printStackTrace();
							}
							succes.add("Inscription termin??e, veuillez vous connecter");
							request.setAttribute(ERREUR, erreurs);
							response.sendRedirect(CHEMIN_CONNEXION);
						}
					}else {
						erreurs.add("T??l??phone non francophone");
						request.setAttribute(ERREUR, erreurs);
					}
				} else {
					erreurs.add("Date de naissance incorrecte");
					request.setAttribute(ERREUR, erreurs);
				}
			} else {
				erreurs.add("Format non alphanum??rique");
				request.setAttribute(ERREUR, erreurs);
			}
		} else {
			erreurs.add("Utilisateur d??j?? pr??sent");
		}
		request.setAttribute(ERREUR, erreurs);
		request.setAttribute(SUCCES, succes);
		this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
		erreurs.clear();
	}
}
