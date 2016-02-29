package com.cgi.test.dao;

import java.util.List;

public interface BaseDao<T> extends AbstractDao<T> {

    public List<T> getAll();

    public T getById(int id);
}
