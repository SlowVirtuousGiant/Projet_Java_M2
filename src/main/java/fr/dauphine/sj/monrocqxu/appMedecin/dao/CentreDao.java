package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class CentreDao {
	
	public Centre getCentreByID(int centre_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Centre centre = (Centre) session.get(Centre.class, centre_id);
		return centre;
	}
	
	public List<Centre> getAllCentre(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Centre> list = (List<Centre>) session.createSQLQuery("SELECT * FROM centre").addEntity(Centre.class).list();
		return list;
	}
}
