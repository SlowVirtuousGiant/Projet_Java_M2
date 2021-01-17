package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements PersonneDao {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	/*@Autowired
	private SessionFactory sessionFactory;
	*/
	
	
	public List getUserDetails() {
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Personne.class);
        Root contactRoot = criteria.from(Personne.class);
        criteria.select(contactRoot);
        return session.createQuery(criteria).getResultList();
	
	}

}
