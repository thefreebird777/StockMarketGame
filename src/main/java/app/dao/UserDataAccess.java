package app.dao;

import app.exceptions.APIException;
import org.hibernate.models.User;
import org.hibernate.HibernateUtil;
import org.hibernate.Session;

public class UserDataAccess extends DataAccessOperations {

    private static final HibernateUtil DB_CLIENT = new HibernateUtil();
    private static Session session;
    
    public UserDataAccess() {
        session = DB_CLIENT.getSessionFactory().openSession();
    }

   /**
     * Gets an account object from the database using an email ID
     * @param element - User object
     * @param id - User's email
     * @return - User object
     * @throws APIException 
     */
    @Override
    public synchronized User select(Object element, String id) throws APIException {
        try {
            return (User)session.get(element, id);
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }          
    }
    
    /**
     * Updates an account based off a user account
     * @param obj - User object
     * @param id - User's email
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public synchronized int saveOrUpdate(Object obj, String id) throws APIException {
        try {
            session.saveOrUpdate(obj, id);
            return 200;
        } catch (Exception e) {
            throw new APIException(500, e.getMessage(), "src/main/java/app/dao/DataAccess.select()");
        }    
    }

    /**
     * Adds an account to the table
     * @param obj - User object
     * @param id - User's email
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
     * @param obj - User object
     * @param id - User's email
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
    
    public boolean verifyUserCredentials(String email, String password) throws APIException {
        Object ref = new User();
        try {
            User user = (User)session.get(ref, email);
            if(user.getEmail().equals(email) && user.getPassword().equals(password))
                return true;
            else
                return false;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        } 
    }
}