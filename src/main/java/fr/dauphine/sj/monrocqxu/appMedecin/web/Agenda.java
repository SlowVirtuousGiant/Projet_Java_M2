package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil;

public class Agenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	HashMap<Integer, Affectation> affectationCentres;
	List<Centre> centres;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			affectationCentres = new HashMap<Integer,Affectation>();
			centres = new ArrayList<Centre>();
			if (utilisateur != null && utilisateur.getRole().equals("MEDECIN")) {
				
				for(Affectation aff : AffectationDao.getAffectationMedecin(utilisateur.getId())){
					Centre c = CentreDao.getCentreByID(aff.getCentre_id());
					affectationCentres.put(c.getId(), aff);
					centres.add(c);
				}
				
				request.setAttribute("centres_utilisateur", centres);
				session = request.getSession();
				this.getServletContext().getRequestDispatcher("/agenda.jsp").forward(request, response);
				
				//get agenda actif affectation = disponible
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("init") != null){
			//ajouter a la bdd le nom du medecin + la semaine + id affectation
		}
		if(request.getParameter("majAgenda") != null) {
			Centre centre = (Centre) session.getAttribute("selectedCentre");
			
			if(centre != null) {
				System.out.println(affectationCentres);
				Affectation affectation = affectationCentres.get(centre.getId());
				affectation.setDisponible(!affectation.isDisponible());
				AffectationDao.update(affectation);
			}
			
		}
		
		if(request.getParameter("centreSelect") != null){
			session.setAttribute("selectedCentre", CentreDao.getCentreByID(Integer.valueOf(request.getParameter("centreSelect"))));
		}
		
		if(request.getParameter("selectedWeek") != null){
			session.setAttribute("selectedWeek", request.getParameter("selectedWeek"));
		}else {
			session.setAttribute("selectedWeek", TimeMedecinUtil.getCurrentWeek());
		}
		request.setAttribute("centres_utilisateur", centres);
		
		this.getServletContext().getRequestDispatcher("/agenda.jsp").forward( request, response );
	}

}
