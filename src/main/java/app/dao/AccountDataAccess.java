package app.dao;

import org.hibernate.Session;
import app.exceptions.APIException;
import org.hibernate.HibernateUtil;
import org.hibernate.models.Account;

public class AccountDataAccess extends DataAccessOperations {      
    
    private static final HibernateUtil DB_CLIENT = new HibernateUtil();
    private static Session session;
    
    /**
     * Default constructor
     */
    public AccountDataAccess() {
        session = DB_CLIENT.getSessionFactory().openSession();
    }
    
    /**
     * Gets an account object from the database using an email ID
     * @param element - 
     * @param id - user's email
     * @return - Account object
     * @throws APIException 
     */
    @Override
    public synchronized Account select(Object element, String id) throws APIException {
        try {
            return (Account)session.get(element, id);
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }         
    }
    
    /**
     * Updates an account based off a user account
     * @param element
     * @param id - object table id
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
     * @param obj - Account object
     * @param id - object table id
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
     * @param obj - Account object
     * @param id - object table id
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