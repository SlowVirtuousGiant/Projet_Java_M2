package fr.dauphine.sj.monrocqxu.appMedecin.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.dauphine.sj.monrocqxu.appMedecin.model.Affectation;
import fr.dauphine.sj.monrocqxu.appMedecin.model.AgendaModel;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Centre;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Specialite;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;
import fr.dauphine.sj.monrocqxu.appMedecin.web.Agenda;

/**
 * Java based configuration
 * @author ramesh Fadatare
 *
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/rdvmedecin");
                settings.put(AvailableSettings.USER, "root");
                settings.put(AvailableSettings.PASS, "");
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(AvailableSettings.SHOW_SQL, "true");

                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(AvailableSettings.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Utilisateur.class);
                configuration.addAnnotatedClass(Centre.class);
                configuration.addAnnotatedClass(Rdv.class);
                configuration.addAnnotatedClass(Affectation.class);
                configuration.addAnnotatedClass(Specialite.class);
                configuration.addAnnotatedClass(AgendaModel.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
