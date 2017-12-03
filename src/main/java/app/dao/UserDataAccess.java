package app.dao;

import app.bo.DataAccessOperations;
import app.exceptions.APIException;

public class UserDataAccess implements DataAccessOperations {

    @Override
    public String select(String json, String objClass, int id) throws APIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveOrUpdate(String json, String objClass) throws APIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remove(String json, String objClass) throws APIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int put(String json, String objClass) throws APIException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}