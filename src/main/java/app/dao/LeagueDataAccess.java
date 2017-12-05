package app.dao;

import app.exceptions.APIException;
import org.hibernate.HibernateUtil;
import org.hibernate.Session;

public class LeagueDataAccess extends DataAccessOperations {
    
    private static final HibernateUtil DB_CLIENT = new HibernateUtil();
    private static Session session;
    
    public LeagueDataAccess() {
        session = DB_CLIENT.getSessionFactory().openSession();
    }

   /**
     * Gets an account object from the database using an email ID
     * @param element - league object
     * @param id - league's id
     * @return - Account object
     * @throws APIException 
     */
    @Override
    public synchronized Object select(Object element, String id) throws APIException {
        try {
            return session.get(element, id);
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }         
    }
    
    /**
     * Updates an account based off a user account
     * @param element - league object
     * @param id - league's id
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public synchronized int saveOrUpdate(Object element, String id) throws APIException {
        try {
            session.saveOrUpdate(element, id);
            return 200;
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }    
    }

    /**
     * Adds an account to the table
     * @param element - league object
     * @param id - league's id
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public int add(Object obj, String id) throws APIException {
        try {
            session.add(obj, id);
            return 200;
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }     
    }

    /**
     * Removes an account from the table
     * @param element - league object
     * @param id - league's id
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public int remove(Object obj, String id) throws APIException {
        try {
            session.remove(obj, id);
            return 200;
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }    
    }
}