package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

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
		String email = AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("email"));
		String password = AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("motdepasse"));

		if(!validationEmail(email)) {
			erreurs.add("Email non valide.");
		}

		if(!validationMotDePasse(password)) {
			erreurs.add("Mot de passe non valide.");
		}

		if ( erreurs.isEmpty() ) {
			Utilisateur utilisateur = UtilisateurDao.validate(email, password);
			if(utilisateur != null) {
				if(utilisateur.isActif()==true) {
					session.setAttribute( ATT_SESSION_USER, utilisateur);
					session.setAttribute(ATT_SESSION_CENTRES, CentreDao.getAllCentre());
					session.setAttribute(ATT_SESSION_SPECIALITES, SpecialiteDao.getAllSpecialite());
					
					response.sendRedirect( CHEMIN_ESPACE );
				}else {
					erreurs.add("Compte désactivé.");
				}
			}else {
				erreurs.add("Erreur d'authentification.");
			}
		}
		request.setAttribute( ERREUR, erreurs );
		this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response );
		erreurs.clear();		
	}



}