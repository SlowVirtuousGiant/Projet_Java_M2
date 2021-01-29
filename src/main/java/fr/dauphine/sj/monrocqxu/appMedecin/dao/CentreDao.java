package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class CentreDao {
	
	public Centre getCentre(int centre_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Centre centre = (Centre) session.createQuery("from centre where centre_id = :id").setParameter("id", centre_id).uniqueResult();
		return centre;
	}
	
	public List<Centre> getAllCentre(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Centre> list = (List<Centre>) session.createSQLQuery("SELECT * FROM centre").addEntity(Centre.class).list();
		return list;

	}
}
