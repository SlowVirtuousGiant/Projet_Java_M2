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
import fr.dauphine.sj.monrocqxu.appMedecin.dao.AgendaDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.AgendaModel;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
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
			affectationCentres = new HashMap<Integer, Affectation>();
			centres = new ArrayList<Centre>();
			if (utilisateur != null && utilisateur.getRole().equals("MEDECIN")) {

				for (Affectation aff : AffectationDao.getAffectationMedecin(utilisateur.getId())) {//On recherche les centre de ce medecin
					Centre c = CentreDao.getCentreByID(aff.getCentre_id());
					affectationCentres.put(c.getId(), aff);
					centres.add(c);
				}

				request.setAttribute("centres_utilisateur", centres);
				request.setAttribute("affectationCentres", affectationCentres);
				session = request.getSession();
				this.getServletContext().getRequestDispatcher("/agenda.jsp").forward(request, response);

			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("raison") != null) {//Si annulation
			Rdv rdv = RdvDao.getRdvByID(Integer.valueOf(request.getParameter("rdvIdPourAnnulation")));
			Utilisateur patient = UtilisateurDao
					.getUtilisateurByID(Integer.valueOf(request.getParameter("rdvIdPourAnnulation")));
			rdv.setActif(false);
			rdv.setAuteur("MEDECIN");
			rdv.setCommentaire(request.getParameter("raison"));
			RdvDao.update(rdv);
			try {
				MailManager.envoiRDVDetail(patient, rdv);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("selectedWeek", request.getParameter("semaine"));
		}

		if (request.getParameter("init") != null) {//Si initialisation
			int week = Integer.valueOf(request.getParameter("week"));
			int affectationId = Integer.valueOf(request.getParameter("aff_id"));
			AgendaModel agenda = new AgendaModel();
			agenda.setSemaine(week);
			agenda.setAffectation_id(affectationId);
			AgendaDao.ajouter(agenda);
			session.setAttribute("selectedWeek", request.getParameter("week"));

		}
		if (request.getParameter("majAgenda") != null) {//Si l'agenda on change l'activite de l'agenda
			Centre centre = (Centre) session.getAttribute("selectedCentre");

			if (centre != null) {
				Affectation affectation = affectationCentres.get(centre.getId());
				affectation.setDisponible(!affectation.isDisponible());
				AffectationDao.update(affectation);
			}
		}

		//Selection des centres et des semaines
		if (request.getParameter("centreSelect") != null) {
			session.setAttribute("selectedCentre",
					CentreDao.getCentreByID(Integer.valueOf(request.getParameter("centreSelect"))));
		}

		if (request.getParameter("selectedWeek") != null) {
			session.setAttribute("selectedWeek", request.getParameter("selectedWeek"));
		} else if (request.getParameter("week") != null) {
			session.setAttribute("selectedWeek", request.getParameter("week"));
		} else if (session.getAttribute("selectedWeek") == null) {
			session.setAttribute("selectedWeek", TimeMedecinUtil.getCurrentWeek());

		}
		request.setAttribute("centres_utilisateur", centres);
		request.setAttribute("affectationCentres", affectationCentres);

		this.getServletContext().getRequestDispatcher("/agenda.jsp").forward(request, response);
	}

}
