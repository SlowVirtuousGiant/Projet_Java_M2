package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_VISU_RDV;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

public class AnnulationRdv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> erreurs = new ArrayList<String>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {
				String[] idrdv = request.getQueryString().split("=");
				Rdv rdv = RdvDao.getRdvByID(Integer.parseInt(idrdv[1]));
				if (rdv.getPatient_id() == (utilisateur.getId())) {
					request.getSession().setAttribute("rdv", rdv);// save dans la session
					this.getServletContext().getRequestDispatcher("/annulationrdv.jsp").forward(request, response);
				} else {
					response.sendRedirect(CHEMIN_ESPACE);
				}
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Rdv rdv = (Rdv) request.getSession().getAttribute("rdv");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);

		rdv.setActif(false);
		rdv.setCommentaire(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("raison")));
		rdv.setAuteur(utilisateur.getRole());

		if (RdvDao.update(rdv)) {
			try {
				MailManager.envoiRDVDetail(utilisateur, rdv);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(CHEMIN_VISU_RDV);
		} else {
			this.getServletContext().getRequestDispatcher("/visualisationrdv.jsp").forward(request, response);
			request.setAttribute(ERREUR, erreurs);
		}
	}

}
