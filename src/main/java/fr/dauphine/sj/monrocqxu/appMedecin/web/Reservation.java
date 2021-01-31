package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AssignementDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Assignement;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CentreDao centreDao;
	private UtilisateurDao utilisateurDao;
	private AssignementDao assignementDao;
	private SpecialiteDao specialiteDao;
	ArrayList<Assignement> resAssignement = new ArrayList<Assignement>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {

				centreDao = new CentreDao();
				utilisateurDao = new UtilisateurDao();
				specialiteDao = new SpecialiteDao();

				request.setAttribute("specialites", specialiteDao.getAllSpecialite());
				request.setAttribute("centres", centreDao.getAllCentre());
				this.getServletContext().getRequestDispatcher("/reservation.jsp").forward(request, response);
//				System.out.println("centre : ");
//				System.out.println(centreDao.getAllCentre());
//				System.out.println("medecin : ");
//				System.out.println(utilisateurDao.getAllMedecin());
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		assignementDao = new AssignementDao();
		// request.getParameter("sp_id").equals(assignement.getSpecialite_id())&&
		// request.getParameter("ct_id").equals(assignement.getCentre_id()))
		List<Assignement> assignements = findWithNom(request.getParameter("rechercheNom"));
		request.setAttribute( "assignements", assignements);
		
		this.getServletContext().getRequestDispatcher("/reservation.jsp").forward( request, response );
		//response.sendRedirect(CHEMIN_RESERVATION);
	}

	public List<Assignement> findWithNom(String search) {
		HashMap<Integer, String> medecins = utilisateurDao.getAllMedecinName();
		List<Assignement> res = new ArrayList<Assignement>();
		for(Entry<Integer, String> entry : medecins.entrySet()) {
		    if(entry.getValue().contains(search)) {
		    	res.addAll(assignementDao.getAssignement(entry.getKey()));
		    }
		}
		return res;
	}

}