package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class Modification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request) ) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			if(utilisateur!=null && !utilisateur.getRole().equals("ADMIN")) {
				this.getServletContext().getRequestDispatcher("/modification.jsp").forward( request, response );
			}else {
				response.sendRedirect( CHEMIN_ESPACE );
			}
		}else {
			response.sendRedirect( CHEMIN_CONNEXION );
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		System.out.println("bouton confirmer modif");
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
		if(request.getParameter("motdepasse")==null && !validationMotDePasse(request.getParameter("motdepasse"))) {
			erreurs.add("Mot de passe non valide.");
		}
System.out.println("erreurs");
		if ( erreurs.isEmpty() ) {

			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setAdresse(request.getParameter("adresse"));
			utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
			utilisateur.setVille(request.getParameter("ville"));

			UtilisateurDao utilisateurDao = new UtilisateurDao();
			if(utilisateurDao.update(utilisateur)) {
				response.sendRedirect( CHEMIN_PROFIL );
			}else {
				System.out.println("erreurs");
				response.sendRedirect(CHEMIN_MODIFICATION);
				request.setAttribute( ERREUR, erreurs );
			}


		}

	}
}
