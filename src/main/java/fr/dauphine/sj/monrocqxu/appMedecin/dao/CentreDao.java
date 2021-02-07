package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class CentreDao {
	
	public static Centre getCentreByID(int centre_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Centre centre = session.get(Centre.class, centre_id);
		return centre;
	}
	
	public static List<Centre> getAllCentre(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Centre> list = session.createSQLQuery("SELECT * FROM centre EXCEPT SELECT * FROM centre where centre_id = 10").addEntity(Centre.class).list();
		return list;
	}
}
