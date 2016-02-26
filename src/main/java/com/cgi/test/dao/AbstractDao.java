package com.cgi.test.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<K extends Serializable, T> {

    private final Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public Integer save(T entity) {
        return (Integer) getSession().save(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(entityClass);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
