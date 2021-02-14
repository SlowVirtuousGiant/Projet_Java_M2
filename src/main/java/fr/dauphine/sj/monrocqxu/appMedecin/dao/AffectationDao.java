package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class AffectationDao {
	
	public static boolean ajouter (Affectation affectation) {
		Session session = null; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(affectation);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false; 
	}
	
	public void supprimer(Affectation affectation) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.createQuery("delete from affectation where assignement_id = :id").setParameter("id", affectation.getId()).uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean agendaActif(int centre_id, int medecin_id) {//Recupere la disponibilite du medecin (agenda actif)
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean actif =  (boolean) session.createSQLQuery("SELECT disponible FROM affectation WHERE medecin_id = :medecin_id and centre_id = :centre_id").setParameter("medecin_id", medecin_id).setParameter("centre_id",centre_id).uniqueResult();
		return actif;
	}
	
	public static List<Affectation> getAffectationMedecinActif(Utilisateur medecin){
		return getAffectationMedecinActif(medecin.getId());
	}
	
	public static List<Affectation> getAffectationMedecinActif(int medecin_id){//Recupere toutes les affectations avec des medecins disponibles
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list =  session.createSQLQuery("SELECT * FROM affectation WHERE medecin_id = :id and disponible = 1").setParameter("id", medecin_id).addEntity(Affectation.class).list();
		return list;
	}
	
	public static List<Affectation> getAffectationMedecin(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list = session.createSQLQuery("SELECT * FROM affectation WHERE medecin_id = :id").setParameter("id", medecin_id).addEntity(Affectation.class).list();
		return list;
	}
	
	
	public static List<Affectation> getAffectationBySpecialite(int specialite_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list = session.createSQLQuery("SELECT * FROM affectation WHERE specialite_id = :id").setParameter("id", specialite_id).addEntity(Affectation.class).list();
		return list;
	}
	
	public static List<Affectation> getAffectationByCentre(int centre_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list = session.createSQLQuery("SELECT * FROM affectation WHERE centre_id = :id").setParameter("id", centre_id).addEntity(Affectation.class).list();
		return list;
	}
	
	
	public static List<Affectation> getAllAffectation(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list = session.createSQLQuery("SELECT * FROM affectation EXCEPT SELECT * FROM affectation WHERE affectation_id = 16").addEntity(Affectation.class).list();
		return list;
	}
	
	public static Affectation getAffectationByID(int affectation_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Affectation affectation = session.get(Affectation.class, affectation_id);
		return affectation;
	}
	
	public static boolean update (Affectation aff) {
		Session session = null; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(aff);
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
