package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.validationAnneeNaiss;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.validationAlphaNum;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

public class Ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && !utilisateur.getRole().equals("ADMIN")) {
				response.sendRedirect(CHEMIN_ESPACE);
			} else {

				request.setAttribute("specialites", SpecialiteDao.getAllSpecialite());
				request.setAttribute("centres", CentreDao.getAllCentre());
				this.getServletContext().getRequestDispatcher("/ajout.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("nom")));
		utilisateur.setPrenom(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("prenom")));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setAdresse(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("adresse")));
		utilisateur.setMail(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("mail")));
		utilisateur.setNaissance(Integer.parseInt(request.getParameter("naissance")));
		utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
		utilisateur.setVille(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("ville")));
		utilisateur.setRole("MEDECIN");
		String mdp = request.getParameter("motdepasse");
		utilisateur.setMotdepasse(BCrypt.hashpw(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("motdepasse")), BCrypt.gensalt(12)));
		utilisateur.setSexe(request.getParameter("sexe"));
		utilisateur.setActif(true);
		if(!UtilisateurDao.isPresent(request.getParameter("mail"))) {
			if(validationAlphaNum(utilisateur.getNom())&& validationAlphaNum(utilisateur.getPrenom())) {
				if(validationAnneeNaiss(utilisateur.getNaissance())) {
					boolean result = UtilisateurDao.ajouter(utilisateur);

					Affectation affectation = new Affectation();

					if (result) {
						List<Centre> moncentre = CentreDao.getAllCentre();
						for (Centre centre : moncentre) {

							String id = String.valueOf(centre.getId());
							if (request.getParameter("centre_" + id) != null) {
								affectation.setCentre_id(centre.getId());
								System.out.println(centre.getId());
								affectation.setMedecin_id(utilisateur.getId());
								affectation.setSpecialite_id(Integer.valueOf(request.getParameter("sp_"+id)));
								affectation.setDisponible(false);
								AffectationDao.ajouter(affectation);
								MailManager.envoiInscriptionMail(utilisateur,mdp);
								erreurs.add("Utilisateur bien ajouté");
								request.setAttribute( ERREUR, erreurs );
								this.getServletContext().getRequestDispatcher("/ajout.jsp").forward( request, response );
							}
						}
					}
					response.sendRedirect(CHEMIN_AJOUT);
				}else {
					erreurs.add("Date de naissance incorrecte");
					request.setAttribute( ERREUR, erreurs );
				}
			}else {
				erreurs.add("Format non alphanumérique");
				request.setAttribute( ERREUR, erreurs );
			}
		}else {
			erreurs.add("Utilisateur déjà présent");
		}
		request.setAttribute( ERREUR, erreurs );
		this.getServletContext().getRequestDispatcher("/ajout.jsp").forward( request, response );
		erreurs.clear();
	}

}
