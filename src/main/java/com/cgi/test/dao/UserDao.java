package com.cgi.test.dao;

import com.cgi.test.model.User;
import java.util.List;

public interface UserDao {

    public int save(User user);

    public List<User> getAll();

    public User getById(int id);

    public void delete(User user);

}
