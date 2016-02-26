package com.cgi.test.service;

import com.cgi.test.dao.UserDao;
import com.cgi.test.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserService {
    final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    public int save(User user) {
        log.info("save(): user={}", user);
        int id = userDao.save(user);
        log.info("save(): user saved. user={}", user);
        return id;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
