package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.List;

import org.hibernate.Session;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class SpecialiteDao {
	
	public Specialite getSpecialiteByID(int specialite_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Specialite specialite = (Specialite) session.get(Specialite.class, specialite_id);
		return specialite;
	}
	
	public List<Specialite> getAllSpecialite(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Specialite> list = (List<Specialite>) session.createSQLQuery("SELECT * FROM specialite").addEntity(Specialite.class).list();
		return list;

	}

}
