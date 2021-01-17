package fr.dauphine.sj.monrocqxu.rdvmedecin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class TestHibernateInsert {
	/*@PersistenceContext
	private static EntityManager entityManager;
	*/
	@Autowired
	private static SessionFactory sessionFactory;
    public static void main(String[] args) 
    {

    	
    	EntityManager EM = sessionFactory.createEntityManager();
    	System.out.println(EM);
    	Session session = null;
        if (EM == null
        	    || (session = EM.unwrap(Session.class)) == null) {

        	    throw new NullPointerException();
        	} 
    	
        session.beginTransaction();
        
        //Add new Employee object
        Personne emp = new Personne();
        emp.setFirstName("TOTO");
        emp.setLastName("ARLEQUIN");
         
        //Save the employee in database
        session.save(emp);
 
        //Commit the transaction
        session.getTransaction().commit();
       	session.close();
    }
}