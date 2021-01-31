package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_INSCRIPTION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_MODIFICATION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_PROFIL;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_DECONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

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

public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request) ) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			if(utilisateur!=null && !utilisateur.getRole().equals("ADMIN")) {
				this.getServletContext().getRequestDispatcher("/profil.jsp").forward( request, response );
			}else {
				response.sendRedirect( CHEMIN_ESPACE );
			}
		}else {
			response.sendRedirect( CHEMIN_CONNEXION );
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession();

		UtilisateurDao utilisateurDao = new UtilisateurDao();
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
		System.out.println("dans le post dopost");
		
		if(BCrypt.checkpw(request.getParameter("motdepasse"), utilisateur.getMotdepasse())) {
			if(utilisateur.getRole()=="PATIENT") {
				utilisateur.setActif(false);
				if(utilisateurDao.update(utilisateur)) {
					response.sendRedirect( CHEMIN_DECONNEXION );
				}else {
					System.out.println("erreurs");
					response.sendRedirect(CHEMIN_PROFIL);
					request.setAttribute( ERREUR, erreurs );
				}

			}else {
				System.out.println("passage en role inactif pour docteur");
				utilisateur.setActif(false);
				if(utilisateurDao.update(utilisateur)) {
					System.out.println("actif bien MAJ dans la bdd");
					response.sendRedirect( CHEMIN_DECONNEXION );
				}else {
					System.out.println("erreurs");
					response.sendRedirect(CHEMIN_PROFIL);
					request.setAttribute( ERREUR, erreurs );
				}
				//IS MEDECIN CHECK ALL RDV CANCELED
			}
		}else {
			System.out.println("mdp pas bon");
			this.getServletContext().getRequestDispatcher("/profil.jsp").forward( request, response );
			erreurs.add("Mot de passe incorrect");
			request.setAttribute(ERREUR, erreurs);
		}
		
		
	}
}