package fr.dauphine.sj.monrocqxu.appMedecin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

}