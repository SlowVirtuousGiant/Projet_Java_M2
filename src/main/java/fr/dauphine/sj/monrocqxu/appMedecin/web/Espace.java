package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;

public class Espace extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		if (isAuthenticated(request) == false) {
			response.sendRedirect( CHEMIN_CONNEXION );
		}else {
			this.getServletContext().getRequestDispatcher("/espace.jsp").forward( request, response );
		}
		
		CentreDao centreDao = new CentreDao();
		List<Centre> moncentre = centreDao.getAllCentre();
    	System.out.println("toto");
    	System.out.println(moncentre);
		
	}

}
