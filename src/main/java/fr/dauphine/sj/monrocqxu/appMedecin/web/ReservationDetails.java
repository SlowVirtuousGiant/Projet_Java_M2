package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil;

public class ReservationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Utilisateur medecin;
	private Centre centre;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {
				String[] idaffectation = request.getQueryString().split("=");
				if(idaffectation != null && idaffectation.length >= 1 && idaffectation[0].equals("a")) {
					Affectation affectation = AffectationDao.getAffectationByID(Integer.parseInt(idaffectation[1]));
					if(affectation != null) {
						request.setAttribute("selectedDate", TimeMedecinUtil.getFirstRdvDay());
						request.getSession().setAttribute("affectation", idaffectation[1]);//save dans la session
						
						UtilisateurDao utilisateurDao = new UtilisateurDao();
						medecin =  utilisateurDao.getUtilisateurByID(affectation.getMedecin_id());
						centre = CentreDao.getCentreByID(affectation.getCentre_id());
						
						this.getServletContext().getRequestDispatcher("/reservationdetails.jsp").forward(request, response);
					}
				}
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		}else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		String date = request.getParameter("date_id");
		request.setAttribute("selectedDate", date);
		
		if(request.getParameter("rdvCreneau") != null && request.getParameter("rdvDate") != null) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			
			Rdv rdv = new Rdv();
			rdv.setMedecin_id(medecin.getId());
			rdv.setPatient_id(utilisateur.getId());
			rdv.setCentre_id(centre.getId());
			rdv.setCreneau(Integer.valueOf(request.getParameter("rdvCreneau")));
			rdv.setSpecialite_id(Integer.valueOf(request.getParameter("rdvSpecialite")));
			String rdvDate = request.getParameter("rdvDate");
			rdv.setDate(rdvDate);
			rdv.setSemaine(TimeMedecinUtil.getWeekFromDate(rdvDate));
			rdv.setActif(true);
			
			RdvDao.ajouter(rdv);
			response.sendRedirect(CHEMIN_ESPACE);
		}
		this.getServletContext().getRequestDispatcher("/reservationdetails.jsp").forward( request, response );
		
	}
}
