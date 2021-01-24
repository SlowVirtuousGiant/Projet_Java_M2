package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;


public class Inscription extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			response.sendRedirect( CHEMIN_ESPACE );
		}else {
			this.getServletContext().getRequestDispatcher("/inscription.jsp").forward( request, response );
		}
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("motdepasse");

	

	}
}
