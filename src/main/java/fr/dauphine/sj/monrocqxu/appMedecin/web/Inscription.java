package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurDao utilisateurDao = new UtilisateurDao();
        System.out.println("Réagit au formulaire");
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        utilisateur.setTelephone(request.getParameter("telephone"));
        utilisateur.setAdresse(request.getParameter("adresse"));
        utilisateur.setMail(request.getParameter("mail"));
        utilisateur.setNaissance(request.getParameter("naissance"));
        utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
        utilisateur.setVille(request.getParameter("ville"));
        utilisateur.setRole("PATIENT");
        utilisateur.setMotdepasse(BCrypt.hashpw(request.getParameter("motdepasse"),BCrypt.gensalt(12)));
        System.out.println("a catch les infos avec le servlet");
        System.out.println(utilisateur.getNom());
        
        utilisateurDao.ajouter(utilisateur);
	}
}
