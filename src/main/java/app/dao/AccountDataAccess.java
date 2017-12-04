package app.dao;

import app.db.HibernateUtil;
import app.exceptions.APIException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class AccountDataAccess extends DataAccessOperations {
    
    private static final HibernateUtil DB_CLIENT = new HibernateUtil();   
    
    public AccountDataAccess() {}
    
    @Override
    public synchronized Object select(Object element, int id) throws APIException {
        try {
            Session session = DB_CLIENT.getSessionFactory().openSession();
            return session.get(element.getClass(), id);
        } catch (HibernateException he) {
            throw new APIException(500, he.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }          
    }
    
    @Override
    public synchronized int saveOrUpdate(Object element) throws APIException {
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

    @Override
    public int add(Object obj) throws APIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remove(Object json) throws APIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}