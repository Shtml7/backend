package dao;

import model.User;

/**
 * Created by Eric on 08-09-16.
 */
public interface UserDao {

    User create(User user) throws Exception;

    User findById(Long id) throws Exception;

    User findByUsername(String username) throws Exception;
}
