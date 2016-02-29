package com.cgi.test.dao;

import com.cgi.test.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<Integer, User> implements UserDao {

}
