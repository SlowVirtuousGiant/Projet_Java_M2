package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements PersonneDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List getUserDetails() {
		Criteria criteria = sessionFactory.openSession().createCriteria(Personne.class);
		return criteria.list();
	}

}
