package com.cgi.test.dao;

import com.cgi.test.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends BaseDao<Integer, User> implements UserDao {

    public int save(User user) {
        return super.save(user);
    }

    public void delete(User user) {
        super.delete(user);
    }
}
