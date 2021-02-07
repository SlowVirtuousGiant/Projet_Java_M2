package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class RdvDao {
	

	public static List<Rdv> getRdvActifPatient(int patient_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = session.createSQLQuery("SELECT * FROM rdv where patient_id = :patient_id and actif = 1")
				.setParameter("patient_id", patient_id)
				.addEntity(Rdv.class).list();
		return rdvs;
	}
	
	public static List<Rdv> getRdvPatient(int patient_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> list = session.createSQLQuery("SELECT * FROM rdv WHERE patient_id = :id")
				.setParameter("id", patient_id)
				.addEntity(Rdv.class).list();
		return list;
	}
	
	public static List<Rdv> getRdvActifMedecin(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs =session.createSQLQuery("SELECT * FROM rdv where medecin_id = :id and actif = 1")
				.setParameter("id", medecin_id)
				.addEntity(Rdv.class).list();
		return rdvs;
	}
	
	public static List<Rdv> getRdvActifMedecinByWeek(int medecin_id, int week){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = session.createSQLQuery("SELECT * FROM rdv where medecin_id = :id and actif = 1 and semaine = :semaine")
				.setParameter("id", medecin_id)
				.setParameter("semaine", week)
				.addEntity(Rdv.class).list();
		return rdvs;
	}
	
	public static List<Rdv> getRdvMedecin(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Rdv> rdvs = (List<Rdv>) session.createQuery("from rdv where medecin_id = :id")
				.setParameter("id", medecin_id)
				.uniqueResult();
		return rdvs;
	}
	
	public static boolean ajouter(Rdv rdv) {
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
	
	public static Rdv getRdvByID(int rdv_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Rdv rdv = session.get(Rdv.class, rdv_id);
		return rdv;
	}
	
	public static List<Integer> getNonPossibleRdvByDate(String date, Utilisateur medecin){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list = session.createSQLQuery("SELECT creneau FROM rdv WHERE date = :date AND medecin_id = :id")
				.setParameter("date", date)
				.setParameter("id", medecin.getId()).list();
				
		return list;
	}
	
	public static List<Integer> getRdv(String date, Utilisateur medecin){
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Integer> list = session.createSQLQuery("SELECT creneau FROM rdv WHERE date = :date AND medecin_id = :id")
				.setParameter("date", date)
				.setParameter("id", medecin.getId()).list();
				
		return list;
	}
	
	public static List<Integer> getCreneauxRdvPatient(Utilisateur patient, String date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> creneauTrouve =  session.createSQLQuery("SELECT creneau FROM rdv C WHERE C.date = :date AND C.patient_id = :patient_id")
				.setParameter("date", date).setParameter("patient_id", patient.getId()).list();
		return creneauTrouve;
	}

	public static boolean update (Rdv rdv) {
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
