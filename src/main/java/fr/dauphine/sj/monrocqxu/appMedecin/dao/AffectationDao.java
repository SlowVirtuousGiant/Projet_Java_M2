package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class AffectationDao {
	
	public boolean ajouter (Affectation affectation) {
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
	
	public static List<Affectation> getAffectation(Utilisateur medecin){
		return getAffectation(medecin.getId());
	}
	
	public static List<Affectation> getAffectation(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list = (List<Affectation>) session.createSQLQuery("SELECT * FROM affectation WHERE medecin_id = :id").setParameter("id", medecin_id).addEntity(Affectation.class).list();
		return list;
	}
	
	public static List<Affectation> getAllAffectation(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Affectation> list = (List<Affectation>) session.createSQLQuery("SELECT * FROM affectation").addEntity(Affectation.class).list();
		return list;
	}
	
	public static Affectation getAffectationByID(int affectation_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Affectation affectation = (Affectation) session.get(Affectation.class, affectation_id);
		return affectation;
	}
}
