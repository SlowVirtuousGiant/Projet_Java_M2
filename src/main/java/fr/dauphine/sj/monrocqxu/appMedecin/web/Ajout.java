package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;
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

	private CentreDao centreDao;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && !utilisateur.getRole().equals("ADMIN")) {
				response.sendRedirect(CHEMIN_ESPACE);
			} else {

				centreDao = new CentreDao();
				SpecialiteDao specialiteDao = new SpecialiteDao();
				request.setAttribute("specialites", specialiteDao.getAllSpecialite());
				request.setAttribute("centres", centreDao.getAllCentre());
				this.getServletContext().getRequestDispatcher("/ajout.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurDao utilisateurDao = new UtilisateurDao();
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

		boolean result = utilisateurDao.ajouter(utilisateur);
		
		AffectationDao affectationDao = new AffectationDao();
		Affectation affectation = new Affectation();

		if (result) {
			List<Centre> moncentre = centreDao.getAllCentre();
			for (Centre centre : moncentre) {

				String id = String.valueOf(centre.getId());
				if (request.getParameter("centre_" + id) != null) {
					affectation.setCentre_id(centre.getId());
					System.out.println(centre.getId());
					affectation.setMedecin_id(utilisateur.getId());
					affectation.setSpecialite_id(Integer.valueOf(request.getParameter("sp_"+id)));
					affectation.setDisponible(false);
					affectationDao.ajouter(affectation);
					MailManager.envoiInscriptionMail(utilisateur,mdp);
				}
			}
		}
		response.sendRedirect(CHEMIN_AJOUT);
	}
}
