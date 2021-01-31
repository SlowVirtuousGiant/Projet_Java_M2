package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.mindrot.jbcrypt.BCrypt;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.util.HibernateUtil;

public class UtilisateurDao {

	public List<Utilisateur> getAllMedecin() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Utilisateur> list = (List<Utilisateur>) session
				.createSQLQuery("SELECT * FROM utilisateur where role = 'MEDECIN' ").addEntity(Utilisateur.class)
				.list();
		return list;

	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public HashMap<Integer, String> getAllMedecinName() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<List<Object>> permission = session.createSQLQuery("SELECT utilisateur_id, nom FROM utilisateur WHERE role = 'MEDECIN'")
				.setResultTransformer(Transformers.TO_LIST).list();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (List<Object> x : permission) {
			map.put((Integer) x.get(0), (String) x.get(1));
		}
		return map;
	}
	
	public Utilisateur getUtilisateurById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Utilisateur utilisateur = (Utilisateur) session.createQuery("from utilisateur where utilisateur_id = :id")
				.setParameter("utilisateur_id", id)
				.uniqueResult();
		return utilisateur;
	}

	public Utilisateur validate(String userName, String password) {
		Transaction transaction = null;
		Utilisateur utilisateur = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			utilisateur = (Utilisateur) session.createQuery("FROM Utilisateur U WHERE U.mail = :mail")
					.setParameter("mail", userName).uniqueResult();
			if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getMotdepasse())) {
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
			utilisateur = (Utilisateur) session.createQuery("FROM Utilisateur U WHERE U.mail = :mail")
					.setParameter("mail", mail).uniqueResult();
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

	public boolean ajouter(Utilisateur utilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(utilisateur);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean modification(Utilisateur utilisateur) {
		String sql = "Update Utilisateur set telephone =?, adresse=?, code_postal=?, ville=?, motdepasse=? where mail=? ";
		Session session = null;
		return false;
	}

	public boolean supppression(Utilisateur utilisateur) {
		String sql = "Update Utilisateur set actif=0 where mail=? ";
		Session session = null;
		return false;
	}

	public boolean reactivation(Utilisateur utilisateur) {
		String sql = "Update Utilisateur set actif=1 where mail=? ";
		Session session = null;
		return false;
	}

}
