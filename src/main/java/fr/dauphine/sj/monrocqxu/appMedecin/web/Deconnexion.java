package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		HttpSession session = request.getSession();


		if (isAuthenticated(request) == false) {
			response.sendRedirect( CHEMIN_CONNEXION );
		}else {
			session.invalidate();
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			this.getServletContext().getRequestDispatcher("/deconnexion.jsp").forward( request, response );
		}

	}
}