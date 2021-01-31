package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AssignementDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Assignement;

public class Espace extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		if (isAuthenticated(request) == false) {
			response.sendRedirect( CHEMIN_CONNEXION );
		}else {
			this.getServletContext().getRequestDispatcher("/espace.jsp").forward( request, response );
		}
		AssignementDao assignementDao = new AssignementDao();
		List<Assignement> assignements = assignementDao.getAssignement(2);
		System.out.println(assignements);
	}

}
