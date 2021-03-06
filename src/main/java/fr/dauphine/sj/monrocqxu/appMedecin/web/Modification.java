package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

public class Modification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request) ) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			if(utilisateur!=null && !utilisateur.getRole().equals("ADMIN")) {
				this.getServletContext().getRequestDispatcher("/modification.jsp").forward( request, response );
			}	else {
				response.sendRedirect( CHEMIN_ESPACE );
			}
		}	else {
			response.sendRedirect( CHEMIN_CONNEXION );
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if(validationTel(request.getParameter("telephone"))) {


			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);

			boolean auth = BCrypt.checkpw(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("motdepasse")), utilisateur.getMotdepasse());

			if(!AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("newmotdepasse")).equals(null) && validationMotDePasse(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("newmotdepasse")))) {
				utilisateur.setMotdepasse(BCrypt.hashpw(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("newmotdepasse")),BCrypt.gensalt(12)));
			}if(!AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("newmotdepasse")).equals(null) && validationMotDePasse(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("newmotdepasse")))) {
				erreurs.add("Mot de passe trop court.");
			}

			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setAdresse(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("adresse")));
			utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
			utilisateur.setVille(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("ville")));



			if(auth) {
				if(UtilisateurDao.update(utilisateur)) {
					response.sendRedirect( CHEMIN_PROFIL );
					session.setAttribute(ATT_SESSION_USER,utilisateur);
				}else {
					response.sendRedirect(CHEMIN_MODIFICATION);
					erreurs.add("N'a pas pu mettre ?? jour pour de sombres raisons");
				}
			}else {
				erreurs.add("Mot de passe incorrect");
				response.sendRedirect(CHEMIN_MODIFICATION);
			}
		} else {
			erreurs.add("T??l??phone non francophone");
			request.setAttribute(ERREUR, erreurs);
		}

		request.setAttribute( ERREUR, erreurs );


	}
}
