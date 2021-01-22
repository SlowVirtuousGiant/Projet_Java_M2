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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;


public class UserDaoImpl implements UtilisateurDao {
    private DaoFactory daoFactory;

   /* 
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;*/

	/*@Autowired
	private SessionFactory sessionFactory;
	*/
	
	
	/*public List getUserDetails() {
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Utilisateur.class);
        Root contactRoot = criteria.from(Utilisateur.class);
        criteria.select(contactRoot);
        return session.createQuery(criteria).getResultList();
	
	}*/
    private final String INSERT_SQL = "INSERT INTO USERS(nom, prenom, telephone, mail, naissance, adresse,code_postal, ville, role, motdepasse) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    public Utilisateur create(final Utilisateur utilisateur) {
    	System.out.println("début create");
    	KeyHolder holder = new GeneratedKeyHolder();
    	System.out.println("after generatekeyholder");
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connexion) throws SQLException {
				System.out.println("connexion établie");
				PreparedStatement ps = connexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, utilisateur.getNom());
				ps.setString(2, utilisateur.getPrenom());
				ps.setString(3, utilisateur.getTelephone());
				ps.setString(4, utilisateur.getMail());
				ps.setString(5, utilisateur.getNaissance());
				ps.setString(6, utilisateur.getAdresse());
				ps.setLong(7, utilisateur.getCode_postal());
				ps.setString(8, utilisateur.getVille());
				ps.setString(9, "MEDECIN");
				ps.setString(10, BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt(12)));
				System.out.println("méthode create :" + utilisateur.getMdp());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		utilisateur.setId(newUserId);
		return utilisateur;
	}	
    
    
	@Override
    public String ajouter(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DaoFactory.createConnection();
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
            
            int i= preparedStatement.executeUpdate();
            
            if (i!=0) 
           	return "SUCCESS";
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "non ajout ";
    }

}
