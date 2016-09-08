package service;

import dao.UserDao;
import model.User;
import sun.reflect.annotation.ExceptionProxy;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Eric on 08-09-16.
 */
@Stateless
public class UserService {

    @Inject
    private UserDao userDao;

    public User addUser(User user) throws Exception {
        return userDao.create(user);
    }

    public User getUserById(Long id) throws Exception {
        return userDao.findById(id);
    }

    public User getUserByUsername(String username) throws Exception {
        return userDao.findByUsername(username);
    }
}
