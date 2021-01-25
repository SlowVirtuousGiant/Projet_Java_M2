package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Espace extends HttpServlet {
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		if (isAuthenticated(request) == false) {
			response.sendRedirect( CHEMIN_CONNEXION );
		}else {
			this.getServletContext().getRequestDispatcher("/espace.jsp").forward( request, response );
		}
		
	}

}
