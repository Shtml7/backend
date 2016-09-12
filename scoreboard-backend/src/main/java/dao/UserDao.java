package dao;

import model.User;

import java.util.List;

/**
 * Created by Eric on 08-09-16.
 */
public interface UserDao {

    User create(User user) throws Exception;

    User findById(Long id) throws Exception;

    User findByUsername(String username) throws Exception;

    List<User> findAll() throws Exception;
}
