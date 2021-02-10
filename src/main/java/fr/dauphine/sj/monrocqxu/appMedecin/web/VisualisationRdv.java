package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class VisualisationRdv extends HttpServlet{
	private static final long serialVersionUID = 1L;


	ArrayList<Affectation> resaffectation = new ArrayList<Affectation>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {

				request.setAttribute("rdvs", RdvDao.getRdvPatient(utilisateur.getId()));

				request.setAttribute("specialites", SpecialiteDao.getAllSpecialite());
				request.setAttribute("centres", CentreDao.getAllCentre());
				this.getServletContext().getRequestDispatcher("/visualisationrdv.jsp").forward(request, response);

			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}


}
