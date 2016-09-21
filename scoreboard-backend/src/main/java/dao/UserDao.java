package dao;

import model.User;

import java.util.List;

/**
 * Created by Eric on 08-09-16.
 * Dao for users
 */
public interface UserDao {

    User create(User user) throws Exception;

    User findById(Long id) throws Exception;

    List<User> findAll() throws Exception;

    User update(User user) throws Exception;
}
