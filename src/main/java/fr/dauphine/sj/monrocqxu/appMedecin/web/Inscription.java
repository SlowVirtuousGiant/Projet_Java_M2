package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_INSCRIPTION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;


public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			response.sendRedirect( CHEMIN_ESPACE );
		}else {
			this.getServletContext().getRequestDispatcher("/inscription.jsp").forward( request, response );
		}
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		Utilisateur utilisateur = new Utilisateur();
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		if(!utilisateurDao.isPresent(request.getParameter("mail"))) {
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setAdresse(request.getParameter("adresse"));
			utilisateur.setMail(request.getParameter("mail"));
			utilisateur.setNaissance(Integer.parseInt(request.getParameter("naissance")));
			utilisateur.setSexe(request.getParameter("sexe"));
			utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
			utilisateur.setVille(request.getParameter("ville"));
			utilisateur.setRole("PATIENT");
			utilisateur.setMotdepasse(BCrypt.hashpw(request.getParameter("motdepasse"),BCrypt.gensalt(12)));

			if(utilisateurDao.ajouter(utilisateur)) {
				response.sendRedirect( CHEMIN_CONNEXION );
			}
		}
		else {
			response.sendRedirect(CHEMIN_INSCRIPTION);
			request.setAttribute( ERREUR, erreurs );
		}


	}
}
