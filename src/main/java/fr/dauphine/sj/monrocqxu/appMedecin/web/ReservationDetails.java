package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
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

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AssignementDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Assignement;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class ReservationDetails extends HttpServlet {

	private ArrayList<String> erreurs = new ArrayList<String>();
	private CentreDao centreDao;
	private UtilisateurDao utilisateurDao;
	private AssignementDao assignementDao;
	private SpecialiteDao specialiteDao;
	private Rdv rdv;
	private RdvDao rdvDao;
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
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		// RECUPERE ET FORMALISE UN RDV
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
		Integer medecin_id = Integer.parseInt(request.getParameter("medecin_id"));
		Integer patient_id = utilisateur.getId();
		Integer centre_id = Integer.parseInt(request.getParameter("centre_id"));
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		Integer creneau = Integer.parseInt(request.getParameter("creneau"));

		try {
			Date date_id = formatDate.parse(request.getParameter("date_id"));
		} catch (ParseException e) {
			throw new IllegalStateException(e);
		}
		//rdv.setDate(date_id); Bug sur la date ?? du moins le format ?? ou bug eclipse chez moi
		
		
		//CREATION RDV
		rdv = new Rdv();
		rdv.setMedecin_id(medecin_id);
		rdv.setPatient_id(patient_id);
		rdv.setCentre_id(centre_id);
		rdv.setCreneau(creneau);
		rdv.setActif(true);

		// AJOUT RDV SI INEXISTANT
		if(!rdvDao.isPresent(rdv)) { // si pas présent
			rdvDao.ajouter(rdv);	// ajoute à la bdd
		}

	}
}
