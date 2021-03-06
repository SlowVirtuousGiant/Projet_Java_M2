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

import org.json.JSONArray;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil;

public class ModificationAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && !utilisateur.getRole().equals("MEDECIN")) {
				response.sendRedirect(CHEMIN_ESPACE);
			} else {
				this.getServletContext().getRequestDispatcher("/modificationagenda.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jsonresp = request.getParameter("json");
		String dispo = request.getParameter("dispo");
		String indispo = request.getParameter("indispo");

		if (jsonresp != null) {//Si on obtient une reponse AJAX
			JSONArray arr = new JSONArray(jsonresp);//On recupere l'objet JSON envoye
			for (int i = 0; i < arr.length(); i++) {
				String[] splt = arr.getString(i).split("-");//Separation du string du type date-creneau
				String date = splt[0];
				int creneau = Integer.valueOf(splt[1]);
				Rdv rdv = RdvDao.getRdvWithDateAndCreneau(date, creneau, utilisateur);

				if (dispo != null) {//Si on veut liberer ce creneau 
					if (rdv != null && rdv.getPatient_id() == rdv.getMedecin_id()) {
						Utilisateur patient = UtilisateurDao.getUtilisateurByID(rdv.getPatient_id());
						RdvDao.delete(rdv);
					}
				} else if (indispo != null) {//Si on veut rendre ce creneau indisponible
					if (rdv == null) {
						Rdv newRdv = new Rdv();
						newRdv.setMedecin_id(utilisateur.getId());
						newRdv.setPatient_id(utilisateur.getId());
						newRdv.setCentre_id(10);
						newRdv.setCreneau(creneau);
						newRdv.setSpecialite_id(16);
						String rdvDate = date;
						newRdv.setDate(rdvDate);
						newRdv.setSemaine(TimeMedecinUtil.getWeekFromDate(rdvDate));
						newRdv.setActif(true);
						newRdv.setAuteur(null);
						newRdv.setCommentaire(null);
						newRdv.setEnvoi_mail(true);

						RdvDao.ajouter(newRdv);

					}

				}
			}

		}
		//On retourne la reponse a la fonction AJAX
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.getWriter().print("ok");

	}
}
