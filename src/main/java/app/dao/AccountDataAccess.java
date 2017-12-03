package app.dao;

import app.db.HibernateUtil;
import app.exceptions.APIException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class AccountDataAccess {
    
    private static final HibernateUtil DB_CLIENT = new HibernateUtil();   
    
    private AccountDataAccess() {}
    
    public static synchronized Object select(Object element, int id) throws APIException {
        try {
            Session session = DB_CLIENT.getSessionFactory().openSession();
            return session.get(element.getClass(), id);
        } catch (HibernateException he) {
            throw new APIException(500, he.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }          
    }
    
    public static synchronized int saveOrUpdate(Object element) throws APIException {
        try {
            Session session = DB_CLIENT.getSessionFactory().openSession();
            session.saveOrUpdate(element);
            return 200;
        } catch (HibernateException he) {
            throw new APIException(500, he.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }    
    }
}