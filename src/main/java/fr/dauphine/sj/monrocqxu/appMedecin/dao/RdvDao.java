package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Creneau;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class RdvDao {
	
//	private Session session;
//	
//	public RdvDao() {
//		this.session = HibernateUtil.getSessionFactory().openSession();
//	}

	public List<Rdv> getRdvActifPatient(int patient_id, int actif){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createQuery("from rdv where patient_id = :id and actif = :actif")
				.setParameter("patient_id", patient_id)
				.setParameter("actif", actif)
				.uniqueResult();
		return rdvs;
	}
	
	public List<Rdv> getRdvPatient(int patient_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createQuery("from rdv where patient_id = :id")
				.setParameter("patient_id", patient_id)
				.uniqueResult();
		return rdvs;
	}
	
	public List<Rdv> getRdvActifMedecin(int medecin_id, int actif){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createQuery("from rdv where medecin_id = :id and actif = :actif")
				.setParameter("medecin_id", medecin_id)
				.setParameter("actif", actif)
				.uniqueResult();
		return rdvs;
	}
	
	public List<Rdv> getRdvMedecin(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createQuery("from rdv where medecin_id = :id")
				.setParameter("medecin_id", medecin_id)
				.uniqueResult();
		return rdvs;
	}
	
	
	public void ajouterRdv(int patient_id, int medecin_id, int centre_id, Date date, Creneau creneau) {
		
	}
	
	public void ajouterCommentaireRdv(int rdv_id) {
		
	}

	public void supprimerRdv(Utilisateur utilisateur) {
		
	}
	
}
