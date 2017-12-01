package app.bo;

import app.exceptions.APIException;

public interface DataAccessOperations {
    public String select(String json, String objClass, int id) throws APIException;
    public int update(String json, String objClass) throws APIException;
    public int remove(String json, String objClass) throws APIException;
    public int put(String json, String objClass) throws APIException;
}
