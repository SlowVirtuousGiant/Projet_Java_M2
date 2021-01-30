package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Assignement;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class AssignementDao {
	
	public boolean ajouter (Assignement assignement) {
		Session session = null; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(assignement);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false; 
	}
	
	public void supprimer(Assignement assignement) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.createQuery("delete from assignement where assignement_id = :id").setParameter("id", assignement.getId()).uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Assignement> getAssignement(Utilisateur medecin){
		return getAssignement(medecin.getId());
	}
	
	public List<Assignement> getAssignement(int medecin_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("from assignement a where a.medecin_id = :id");
		q.setParameter("id", medecin_id);
		List<Assignement> list = q.list();
		return list;
	}
}
