package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_AJOUT;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AssignementDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Assignement;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class Reservation extends HttpServlet {
	private CentreDao centreDao;
	private UtilisateurDao utilisateurDao;
	private AssignementDao assignementDao;
	private SpecialiteDao specialiteDao;


	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request) ) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			if(utilisateur!=null && utilisateur.getRole().equals("PATIENT")) {

				centreDao = new CentreDao();
				utilisateurDao = new UtilisateurDao();
				assignementDao = new AssignementDao();
				specialiteDao = new SpecialiteDao();

				request.setAttribute("specialites", specialiteDao.getAllSpecialite());
				request.setAttribute("centres", centreDao.getAllCentre());
				request.setAttribute("medecins", utilisateurDao.getAllMedecin());
				this.getServletContext().getRequestDispatcher("/reservation.jsp").forward( request, response );
				System.out.println("centre : ");
				System.out.println(centreDao.getAllCentre());
				System.out.println("medecin : ");
				System.out.println(utilisateurDao.getAllMedecin());


			}else {
				response.sendRedirect( CHEMIN_ESPACE );
			}
		}else {
			response.sendRedirect( CHEMIN_CONNEXION );
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		utilisateurDao = new UtilisateurDao();
		List<Utilisateur> medecins = utilisateurDao.getAllMedecin();
		centreDao = new CentreDao();
		List<Centre> centres = centreDao.getAllCentre();
		specialiteDao = new SpecialiteDao();
		List<Specialite> specialites = specialiteDao.getAllSpecialite();
		assignementDao = new AssignementDao();
		List<Assignement> assignements = assignementDao.getAllAssignement();		
				
		for(Assignement assignement : assignements) {
			
			if(request.getParameter("rechercherNom").equals(assignement.getMedecin_id())&&
					request.getParameter("sp_id").equals(assignement.getSpecialite_id())&&
							request.getParameter("ct_id").equals(assignement.getCentre_id())){
				
			}
		}
		
//		for(Utilisateur utilisateur : medecins) {
//			if(request.getParameter("rechercherNom").equals(utilisateur.getNom())) {
//				System.out.println(utilisateur.getNom());
//			}
//		}
		response.sendRedirect("appmedecin/reservation");
	}
}