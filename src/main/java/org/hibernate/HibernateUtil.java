package org.hibernate;

public class HibernateUtil {
    
    public HibernateUtil getSessionFactory() {
        return this;
    }
    
    public Session openSession() {
        return new Session();
    }
}