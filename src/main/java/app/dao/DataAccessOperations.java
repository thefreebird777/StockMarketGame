package app.dao;

import app.exceptions.APIException;

public abstract class DataAccessOperations {
    public abstract Object select(Object json, int id) throws APIException;
    public abstract int saveOrUpdate(Object json) throws APIException;
    public abstract int add(Object obj) throws APIException;
    public abstract int remove(Object json) throws APIException;
}
