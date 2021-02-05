package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class RdvDao {
	
//	private Session session;
//	
//	public RdvDao() {
//		this.session = HibernateUtil.getSessionFactory().openSession();
//	}

	public List<Rdv> getRdvActifPatient(int patient_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createSQLQuery("SELECT * FROM rdv where patient_id = :patient_id and actif = 1")
				.setParameter("patient_id", patient_id)
				.addEntity(Rdv.class).list();
		return rdvs;
	}
	
	public List<Rdv> getRdvPatient(int patient_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> list = (List<Rdv>) session.createSQLQuery("SELECT * FROM rdv WHERE patient_id = :id")
				.setParameter("id", patient_id)
				.addEntity(Rdv.class).list();
		return list;
	}
	
	public List<Rdv> getRdvActifMedecin(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createSQLQuery("SELECT * FROM rdv where medecin_id = :id and actif = :actif")
				.setParameter("medecin_id", medecin_id)
				.addEntity(Rdv.class).list();
		return rdvs;
	}
	
	public List<Rdv> getRdvMedecin(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createQuery("from rdv where medecin_id = :id")
				.setParameter("medecin_id", medecin_id)
				.uniqueResult();
		return rdvs;
	}
	
	public boolean ajouter(Rdv rdv) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(rdv);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public Rdv getRdvByID(int rdv_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Rdv rdv = (Rdv) session.get(Rdv.class, rdv_id);
		return rdv;
	}
	
	public static List<Integer> getNonPossibleRdvByDate(String date, Utilisateur medecin){
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Integer> list = (List<Integer>) session.createSQLQuery("SELECT creneau FROM rdv WHERE medecin_id = :id AND date = :date")
				.setParameter("id", medecin.getId())
				.setParameter("date", date).list();
		return list;
	}
	
	public boolean update (Rdv rdv) {
		Session session = null; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(rdv);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false; 
	}
	
}
