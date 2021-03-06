package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class SpecialiteDao {
	
	public static Specialite getSpecialiteByID(int specialite_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Specialite specialite =  session.get(Specialite.class, specialite_id);
		return specialite;
	}
	
	public static List<Specialite> getAllSpecialite(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Specialite> list = session.createSQLQuery("SELECT * FROM specialite EXCEPT SELECT * FROM specialite where specialite_id = 16").addEntity(Specialite.class).list();
		return list;

	}

}
