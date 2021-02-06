package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArrayList<Affectation> resaffectation = new ArrayList<Affectation>();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {
				this.getServletContext().getRequestDispatcher("/reservation.jsp").forward(request, response);
				request.setAttribute("selectedSpecia", "0");// Selection d'une specialite par defaut
				request.setAttribute("rechercherValue", "");
				request.setAttribute("selectedCentres", null);
			} else {
				response.sendRedirect(CHEMIN_ESPACE);
			}
		} else {
			response.sendRedirect(CHEMIN_CONNEXION);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Affectation> resultTmp = new ArrayList<Affectation>();
		List<Affectation> results = new ArrayList<Affectation>();
		
		boolean champNom = false;
		boolean champSpecia = false;

		String recherche = request.getParameter("rechercheNom");
		if (!recherche.equals("")) {//recherche du nom
			champNom = true;
			request.setAttribute("rechercherValue", recherche);
			results = findWithNom(recherche);
		}
		
		
		if (!request.getParameter("sp_id").equals("0")) {//si utilisation specialite
			int specialite = Integer.parseInt(request.getParameter("sp_id"));
			if (champNom) {
				if (!results.isEmpty()) {// si il a des resultat dans la recherche de nom
					resultTmp = new ArrayList<Affectation>(results);
					results.clear();
					for (Affectation affectation : resultTmp) {
						if (affectation.getSpecialite_id() == specialite) {
							results.add(affectation);
						}
					}
				}
			} else {//Si uniquement la specialite est utilisee
				champSpecia = true;
				results = AffectationDao.getAffectationBySpecialite(specialite);
			}
			request.setAttribute("selectedSpecia", request.getParameter("sp_id"));
		}

		String[] centreSelect = request.getParameterValues("selectedCentre");
		
		if (centreSelect != null && centreSelect.length > 0) {//Si un ou plusieurs centre est selectionne
			if (champNom || champSpecia) {
				if (!results.isEmpty()) {
					resultTmp = new ArrayList<Affectation>(results);
					results.clear();
					for(Affectation affectation : resultTmp) {
						for(String centre : centreSelect) {
							int centre_id = Integer.parseInt(centre);
							if(affectation.getCentre_id() == centre_id) {
								results.add(affectation);
							}
						}
					}
					request.setAttribute("affectations", results);
				}
			} else {// query sur toutes les affectations des centres cochés
				for(String centre : centreSelect) {
					System.out.println("ca rentre select unique");
					int centre_id = Integer.parseInt(centre);
					results.addAll(AffectationDao.getAffectationByCentre(centre_id));
				}
			}
			request.setAttribute("selectedCentre", centreSelect);
		}
		request.setAttribute("affectations", results);
		

		this.getServletContext().getRequestDispatcher("/reservation.jsp").forward(request, response);
	}

	private List<Affectation> findWithNom(String search) {
		HashMap<Integer, String> medecins = UtilisateurDao.getAllMedecinName();
		List<Affectation> res = new ArrayList<Affectation>();
		for (Entry<Integer, String> entry : medecins.entrySet()) {
			if (!search.equals("") && entry.getValue().contains(search)) {
				res.addAll(AffectationDao.getAffectationMedecinActif(entry.getKey()));
			}
		}
		return res;
	}

}