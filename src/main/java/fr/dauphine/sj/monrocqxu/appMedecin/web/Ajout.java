package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AssignementDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Assignement;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

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
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setAdresse(request.getParameter("adresse"));
		utilisateur.setMail(request.getParameter("mail"));
		utilisateur.setNaissance(Integer.parseInt(request.getParameter("naissance")));
		utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setRole("MEDECIN");
		utilisateur.setMotdepasse(BCrypt.hashpw(request.getParameter("motdepasse"), BCrypt.gensalt(12)));
		utilisateur.setSexe(request.getParameter("sexe"));

		boolean result = utilisateurDao.ajouter(utilisateur);
		AssignementDao assignementDao = new AssignementDao();
		Assignement assignement = new Assignement();

		System.out.println("Ajout médecin assignement");
		if (result) {
			List<Centre> moncentre = centreDao.getAllCentre();
			for (Centre centre : moncentre) {

				String id = String.valueOf(centre.getId());
				if (request.getParameter("centre_" + id) != null) {
					assignement.setCentre_id(centre.getId());
					System.out.println(centre.getId());
					assignement.setMedecin_id(utilisateur.getId());
					assignement.setSpecialite_id(Integer.valueOf(request.getParameter("sp_"+id)));
					assignementDao.ajouter(assignement);
				}
			}
		}
		response.sendRedirect(CHEMIN_AJOUT);
	}
}
