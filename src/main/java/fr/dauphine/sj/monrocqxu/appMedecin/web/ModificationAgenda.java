package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.TimeMedecinUtil;

public class ModificationAgenda extends HttpServlet{
	
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
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsonresp = request.getParameter("json");
		String dispo = request.getParameter("dispo");
		String indispo = request.getParameter("indispo");

		
		if(jsonresp != null) {
			JSONArray arr = new JSONArray(jsonresp);
			for(int i=0; i < arr.length();i++) {
				String[] splt = arr.getString(i).split("-");
				String date = splt[0];
				int creneau = Integer.valueOf(splt[1]);
				Rdv rdv = RdvDao.getRdvWithDateAndCreneau(date, creneau, utilisateur);
				
				if(dispo != null) {
					if(rdv != null && rdv.getPatient_id() == rdv.getMedecin_id()) {
						RdvDao.delete(rdv);
					}
				}
				else if(indispo != null) {
					if(rdv == null) {
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
						
						RdvDao.ajouter(newRdv);
						
					}
					
				}
			}
			System.out.println("REDIRECT BABYYY");
			this.getServletContext().getRequestDispatcher("/modificationagenda.jsp").forward( request, response );
		}

		
	}
}
