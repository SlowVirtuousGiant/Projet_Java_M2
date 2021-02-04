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

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class VisualisationRdv extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private CentreDao centreDao;
	private UtilisateurDao utilisateurDao;
	private AffectationDao affectationDao;
	private SpecialiteDao specialiteDao;
	private RdvDao rdvDao;
	ArrayList<Affectation> resaffectation = new ArrayList<Affectation>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {

				centreDao = new CentreDao();
				utilisateurDao = new UtilisateurDao();
				specialiteDao = new SpecialiteDao();
				rdvDao  = new RdvDao();
				
				request.setAttribute("rdvs", rdvDao.getRdv(utilisateur.getId()));
				
				System.out.println(rdvDao.getRdv(utilisateur.getId()));

				request.setAttribute("specialites", specialiteDao.getAllSpecialite());
				request.setAttribute("centres", centreDao.getAllCentre());
				this.getServletContext().getRequestDispatcher("/visualisationrdv.jsp").forward(request, response);

			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		affectationDao = new AffectationDao();

		List<Affectation> affectations = findWithNom(request.getParameter("rechercheNom"));
		request.setAttribute( "affectations", affectations);
		
		this.getServletContext().getRequestDispatcher("/visualisationrdv.jsp").forward( request, response );
		//response.sendRedirect(CHEMIN_RESERVATION);
	}

	public List<Affectation> findWithNom(String search) {
		HashMap<Integer, String> medecins = utilisateurDao.getAllMedecinName();
		List<Affectation> res = new ArrayList<Affectation>();
		for(Entry<Integer, String> entry : medecins.entrySet()) {
		    if(entry.getValue().contains(search)) {
		    	res.addAll(affectationDao.getAffectation(entry.getKey()));
		    }
		}
		return res;
	}

}
