package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.AgendaModel;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class AgendaDao {
	
	public static boolean isAgendaInitialise(int semaine, int affectation_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		AgendaModel agenda = (AgendaModel) session.createSQLQuery("SELECT * FROM agenda WHERE affectation_id = :affectation_id AND semaine = :semaine")
				.setParameter("affectation_id", affectation_id).setParameter("semaine", semaine).addEntity(AgendaModel.class).uniqueResult();
		return agenda != null;
	}
	
	public static boolean ajouter (AgendaModel agenda) {
		Session session = null; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(agenda);
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
