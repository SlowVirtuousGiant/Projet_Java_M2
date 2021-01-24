package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import org.mindrot.jbcrypt.BCrypt;

import com.mysql.jdbc.Statement;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;


public class UtilisateurDao {

	public void saveUser(Utilisateur utilisateur) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(utilisateur);
			// commit transaction
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			System.out.println("test bdd");
			utilisateur = (Utilisateur) session.createQuery("FROM Utilisateur U WHERE U.mail = :mail").setParameter("mail", userName)
					.uniqueResult();

			if (utilisateur != null && utilisateur.getMotdepasse().equals(password)) {
				return utilisateur;
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	public void ajouter (Utilisateur utilisateur) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
			session.save(utilisateur);
			session.getTransaction().commit();
			//HibernateUtil.getSessionFactory().
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	/*PreparedStatement preparedStatement = null;
			preparedStatement = session.prepareStatement( "INSERT INTO USERS(nom, prenom, telephone, mail, naissance, adresse,code_postal, ville, role, motdepasse) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, utilisateur.getNom());
			ps.setString(2, utilisateur.getPrenom());
			ps.setString(3, utilisateur.getTelephone());
			ps.setString(4, utilisateur.getMail());
			ps.setString(5, utilisateur.getNaissance());
			ps.setString(6, utilisateur.getAdresse());
			ps.setLong(7, utilisateur.getCode_postal());
			ps.setString(8, utilisateur.getVille());
			ps.setString(9, "MEDECIN");
			ps.setString(10, BCrypt.hashpw(utilisateur.getMotdepasse(), BCrypt.gensalt(12)));
			System.out.println("méthode create :" + utilisateur.getMotdepasse());
			return ps;
		}



} */
