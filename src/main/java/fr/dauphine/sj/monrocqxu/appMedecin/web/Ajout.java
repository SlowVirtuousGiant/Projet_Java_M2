package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class Ajout extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request) ) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			if(utilisateur!=null && !utilisateur.getRole().equals("ADMIN")) {
				response.sendRedirect( CHEMIN_ESPACE );
			}else {
				this.getServletContext().getRequestDispatcher("/ajout.jsp").forward( request, response );
			}
		}else {
			response.sendRedirect( CHEMIN_CONNEXION );
		}



	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		System.out.println("Réagit au formulaire");
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setAdresse(request.getParameter("adresse"));
		utilisateur.setMail(request.getParameter("mail"));
		utilisateur.setNaissance(Integer.parseInt(request.getParameter("naissance")));
		utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setRole("MEDECIN");
		utilisateur.setMotdepasse(BCrypt.hashpw(request.getParameter("motdepasse"),BCrypt.gensalt(12)));
		utilisateur.setSexe("sexe");
		System.out.println("a catch les infos avec le servlet");
		System.out.println(utilisateur.getNom());

		utilisateurDao.ajouter(utilisateur);
	}
}
