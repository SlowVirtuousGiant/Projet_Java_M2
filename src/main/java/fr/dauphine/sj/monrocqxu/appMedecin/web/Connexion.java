package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> erreurs = new ArrayList<String>();

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			response.sendRedirect( CHEMIN_ESPACE );
		}else {
			this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response );
		}
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String password = request.getParameter("motdepasse");

			if(!validationEmail(email)) {
				erreurs.add("Email non valide.");
			}

			if(!validationMotDePasse(password)) {
				erreurs.add("Mot de passe non valide.");
			}

			System.out.println(erreurs);

			if ( erreurs.isEmpty() ) {
				UtilisateurDao utilisateurDao = new UtilisateurDao();
				Utilisateur utilisateur = utilisateurDao.validate(email, password);
				if(utilisateur != null) {
					System.out.println("login succes");
					session.setAttribute( ATT_SESSION_USER, utilisateur);
					response.sendRedirect( "/appMedecin/espace" );
				}else {
					System.out.println("login erreur");
					erreurs.add("Erreur d'authentification.");
				}
			}
			request.setAttribute( ERREUR, erreurs );

			this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response );

			erreurs.clear();
		
	}

	private boolean validationEmail( String email ) {
		if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			return false;
		}
		return true;
	}

	private boolean validationMotDePasse( String motDePasse ) {
		if ( motDePasse != null ) {
			if ( motDePasse.length() < 3 ) {
				return false;
			}
		}
		return true;
	}

}