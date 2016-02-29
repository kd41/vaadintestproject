package com.cgi.test.dao;

import java.util.List;

public interface AbstractDao<T> {

    public void persist(T entity);

    public Integer save(T entity);

    public void saveOrUpdate(T entity);

    public void delete(T entity);
}
