package app.dao;

import app.exceptions.APIException;

public abstract class DataAccessOperations {
    public abstract Object select(Object json, String id) throws APIException;
    public abstract int saveOrUpdate(Object json, String id) throws APIException;
    public abstract int add(Object obj, String id) throws APIException;
    public abstract int remove(Object obj, String id) throws APIException;
}
