package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UtilisateurDao {
   /* private DaoFactory daoFactory;



	public UserDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }*/
    
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	/*@Autowired
	private SessionFactory sessionFactory;
	*/
	
	
	public List getUserDetails() {
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Utilisateur.class);
        Root contactRoot = criteria.from(Utilisateur.class);
        criteria.select(contactRoot);
        return session.createQuery(criteria).getResultList();
	
	}
	/*
	@Override
    public void ajouter(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur (nom, prenom, telephone, mail, naissance, adresse, "
            		+ "code_postal, ville, role, motdepasse) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setString(3, utilisateur.getTelephone());
            preparedStatement.setString(4, utilisateur.getMail());
            preparedStatement.setString(5, utilisateur.getNaissance());
            preparedStatement.setString(6, utilisateur.getAdresse());
            preparedStatement.setLong(7, utilisateur.getCode_postal());
            preparedStatement.setString(8, utilisateur.getVille());
            preparedStatement.setString(9, "MEDECIN");
            preparedStatement.setString(10, BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt(12)));
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/

}
