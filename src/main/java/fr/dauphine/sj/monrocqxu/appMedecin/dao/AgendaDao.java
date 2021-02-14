package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.AgendaModel;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class AgendaDao {
	
	public static boolean isAgendaInitialise(int semaine, int affectation_id){//Fonction pour verifier si un agenda est initialise
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
	
	public static List<Integer> getSemaineInitialisationByAffectation(int affectation_id){//Recherche si un agenda pour une affectation est initialise
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> list =  session.createSQLQuery("SELECT semaine FROM agenda WHERE affectation_id = :id").setParameter("id", affectation_id).list();
		return list;
	}

}
