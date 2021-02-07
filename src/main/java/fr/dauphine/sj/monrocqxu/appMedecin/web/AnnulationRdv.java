package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_DECONNEXION;
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
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.CentreDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.SpecialiteDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil;

public class AnnulationRdv extends HttpServlet{

	private ArrayList<String> erreurs = new ArrayList<String>();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (isAuthenticated(request)) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);
			if (utilisateur != null && utilisateur.getRole().equals("PATIENT")) {
				String[] idrdv = request.getQueryString().split("=");
				Rdv rdv = RdvDao.getRdvByID(Integer.parseInt(idrdv[1]));
				if(rdv.getPatient_id()==(utilisateur.getId())) {
					request.getSession().setAttribute("rdv", rdv);//save dans la session
					this.getServletContext().getRequestDispatcher("/annulationrdv.jsp").forward(request, response);
				}else {
					response.sendRedirect(CHEMIN_ESPACE);
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
		System.out.println("dans le post dopost");
		Rdv rdv = (Rdv)request.getSession().getAttribute("rdv");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute(ATT_SESSION_USER);


		rdv.setActif(false);
		rdv.setCommentaire(AppMedecinUtil.ConvertISOtoUTF8(request.getParameter("raison")));
		rdv.setAuteur(utilisateur.getRole());
		
		System.out.println(rdv.getCommentaire() + " Print avant le update");
		if(RdvDao.update(rdv)) {
			MailManager.envoiRDVDetail(utilisateur, rdv);
			System.out.println(rdv.getCommentaire() + " Print après le update");
			response.sendRedirect(CHEMIN_VISU_RDV);
		}else {
			System.out.println("erreurs");
			this.getServletContext().getRequestDispatcher("/visualisationrdv.jsp").forward( request, response );
			request.setAttribute( ERREUR, erreurs );
		}
	}


}


