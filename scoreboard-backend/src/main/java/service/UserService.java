package service;

import dao.UserDao;
import model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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

    public List<User> getAllUsers() throws Exception {
        return userDao.findAll();
    }
}
