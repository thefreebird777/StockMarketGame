package app.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.models.DefaultInterface;

public class HibernateUtil<T extends DefaultInterface> {

    public HibernateUtil() {}

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
   public static void shutdown() {        
        getSessionFactory().close();
    } 
   
   public static void beginTransaction() {
	   sessionFactory.getCurrentSession().beginTransaction();
   }
   
//   public static void closeTransaction() {
//	   sessionFactory.getCurrentSession().getTransaction().commit();
//   }
}