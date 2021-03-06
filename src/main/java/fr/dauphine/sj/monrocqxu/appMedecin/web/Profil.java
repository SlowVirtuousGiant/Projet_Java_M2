package fr.dauphine.sj.monrocqxu.appMedecin.web;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ATT_SESSION_USER;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_CONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_ESPACE;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_PROFIL;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.CHEMIN_DECONNEXION;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.ERREUR;
import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.isAuthenticated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.dao.AffectationDao;

public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> erreurs = new ArrayList<String>();
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (isAuthenticated(request) ) {
			Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);
			if(utilisateur!=null && !utilisateur.getRole().equals("ADMIN")) {
				this.getServletContext().getRequestDispatcher("/profil.jsp").forward( request, response );
			}else {
				response.sendRedirect( CHEMIN_ESPACE );
			}
		}else {
			response.sendRedirect( CHEMIN_CONNEXION );
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute(ATT_SESSION_USER);

		if(BCrypt.checkpw(request.getParameter("motdepasse"), utilisateur.getMotdepasse())) {
			if(utilisateur.getRole().equals("PATIENT")) {
				utilisateur.setActif(false);
				if(UtilisateurDao.update(utilisateur)) {
					List<Rdv> listRdv = RdvDao.getRdvActifPatient(utilisateur.getId());
					try {
						MailManager.envoiMailDesactivationCompte(utilisateur,listRdv);
					} catch (Exception e) {
						e.printStackTrace();
					}

					for(Rdv rdv:listRdv) {
						rdv.setActif(false);
						RdvDao.update(rdv);
					}
					response.sendRedirect( CHEMIN_DECONNEXION );
				}else {
					response.sendRedirect(CHEMIN_PROFIL);
					request.setAttribute( ERREUR, erreurs );
				}

			}else {
				List<Rdv> listRdv = RdvDao.getRdvActifMedecin(utilisateur.getId());
				if(listRdv!=null && listRdv.isEmpty()) {

					utilisateur.setActif(false);
					if(UtilisateurDao.update(utilisateur)) {
						MailManager.envoiMailDesactivationCompte(utilisateur,null);
						List<Affectation> listAff = AffectationDao.getAffectationMedecinActif(utilisateur.getId());

						for(Affectation aff:listAff) {
							aff.setDisponible(false);
							AffectationDao.update(aff);
						}
						response.sendRedirect( CHEMIN_DECONNEXION );
					}else {
						response.sendRedirect(CHEMIN_PROFIL);
						request.setAttribute( ERREUR, erreurs );
					}
				}else {
					response.sendRedirect(CHEMIN_PROFIL);
					erreurs.add("Le docteur a des rdv");
					request.setAttribute( ERREUR, erreurs );
				}

			}
		}else {
			this.getServletContext().getRequestDispatcher("/profil.jsp").forward( request, response );
			erreurs.add("Mot de passe incorrect");
			request.setAttribute(ERREUR, erreurs);
		}


	}
}