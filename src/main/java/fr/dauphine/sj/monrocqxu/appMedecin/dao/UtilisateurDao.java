package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;


import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;


public class UtilisateurDao {

	public void saveUser(Utilisateur utilisateur) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(utilisateur);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Utilisateur validate(String userName, String password) {
		Transaction transaction = null;
		Utilisateur utilisateur = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			utilisateur = (Utilisateur) session.createQuery("FROM Utilisateur U WHERE U.mail = :mail").setParameter("mail", userName)
					.uniqueResult();
			if (utilisateur != null && BCrypt.checkpw(password,utilisateur.getMotdepasse())) {
				return utilisateur;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		} 
		return null;
	}

	public boolean isPresent(String mail) {
		Transaction transaction = null;
		Utilisateur utilisateur = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			utilisateur = (Utilisateur) session.createQuery("FROM Utilisateur U WHERE U.mail = :mail").setParameter("mail", mail)
					.uniqueResult();
			if (utilisateur != null) {
				System.out.println("Un utilisateur a bien été trouvé");
				return true;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		} 
		return false;
	}


	public boolean ajouter (Utilisateur utilisateur) {
		Session session = null; 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(utilisateur);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false; 
	}
	
	public void supprimer(Utilisateur utilisateur) {
		
	}
}

