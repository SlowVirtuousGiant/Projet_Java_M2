package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_MODIFICATION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_PROFIL;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.validationMotDePasse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

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
	private ArrayList<String> erreurs = new ArrayList<String>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {

				centreDao = new CentreDao();
				utilisateurDao = new UtilisateurDao();
				specialiteDao = new SpecialiteDao();
				rdvDao  = new RdvDao();
	

				request.setAttribute("rdvs", rdvDao.getRdvActifPatient(utilisateur.getId()));

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


}
