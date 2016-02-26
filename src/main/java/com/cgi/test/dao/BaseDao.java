package com.cgi.test.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDao<K extends Serializable, T> extends AbstractDao {

    public List<T> getAll() {
        Criteria criteria = createEntityCriteria();
        return (List<T>) criteria.list();
    }

    public T getById(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }
}
