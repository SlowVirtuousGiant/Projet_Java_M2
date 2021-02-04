package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_AJOUT;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_RESERVATIONDE;

public class ReservationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurDao utilisateurDao;
	private AffectationDao AffectationDao;
	private SpecialiteDao specialiteDao;
	private Rdv rdv;
	private RdvDao rdvDao;
	ArrayList<Affectation> resAffectation = new ArrayList<Affectation>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {
				this.getServletContext().getRequestDispatcher("/reservationdetails.jsp").forward(request, response);
				String[] idaffectation = request.getQueryString().split("=");
				Affectation affectation = new Affectation();
				AffectationDao affectationDao = new AffectationDao();
				affectation = affectationDao.getAffectationByID(Integer.parseInt(idaffectation[1]));
				System.out.println(Integer.parseInt(idaffectation[1]));
				
				Utilisateur medecin = new Utilisateur();
				UtilisateurDao utilisateurDao = new UtilisateurDao();
				medecin = utilisateurDao.getUtilisateurByID(affectation.getMedecin_id());
				request.setAttribute("medecin", medecin);
				System.out.println(medecin.getNom());
				
				Centre centre = new Centre();
				CentreDao centreDao = new CentreDao();
				centre = centreDao.getCentreByID(affectation.getCentre_id());
				request.setAttribute("centre", centre);
				System.out.println(centre.getNom());
				
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
			//} else {
			//response.sendRedirect(CHEMIN_CONNEXION);
			//}
		}
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		String date = request.getParameter("date_id");
		System.out.println(date);
		request.setAttribute("selectedDate", date);
		// RECUPERE ET FORMALISE UN RDV
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
		//		//Integer medecin_id = Integer.parseInt(request.getParameter("medecin_id"));
		//		Integer patient_id = utilisateur.getId();
		//		Integer centre_id = Integer.parseInt(request.getParameter("centre_id"));
		//		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		//		Integer creneau = Integer.parseInt(request.getParameter("creneau"));
		//
		//		try {
		//			Date date_id = formatDate.parse(request.getParameter("date_id"));
		//		} catch (ParseException e) {
		//			throw new IllegalStateException(e);
		//		}
		//		//rdv.setDate(date_id); Bug sur la date ?? du moins le format ?? ou bug eclipse chez moi
		//		
		//		
		//		//CREATION RDV
		//		rdv = new Rdv();
		//		rdv.setMedecin_id(medecin_id);
		//		rdv.setPatient_id(patient_id);
		//		rdv.setCentre_id(centre_id);
		//		rdv.setCreneau(creneau);
		//		rdv.setActif(true);
		//
		//		// AJOUT RDV SI INEXISTANT
		//		if(!rdvDao.isPresent(rdv)) { // si pas présent
		//			rdvDao.ajouter(rdv);	// ajoute à la bdd
		//		}
		this.getServletContext().getRequestDispatcher("/reservationdetails.jsp").forward( request, response );
		//response.sendRedirect(CHEMIN_RESERVATIONDE);
	}
}
