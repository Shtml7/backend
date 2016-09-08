package dao;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Eric on 08-09-16.
 */
public class UserDaoJPA implements UserDao {

    @PersistenceContext(name = "SmartMobilePu")
    private EntityManager entityManager;

    @Override
    public User create(User user) throws Exception {
        try {
            entityManager.persist(user);
            return user;
        } catch (Exception ex) {
            throw new Exception("Could not create user", ex);
        }
    }

    @Override
    public User findById(Long id) throws Exception {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find user with id: " + id, ex);
        }
    }

    @Override
    public User findByUsername(String username) throws Exception {
        try {
            return (User) entityManager.createNamedQuery("findUserByUsername").setParameter("username", username).getSingleResult();
        } catch (Exception ex) {
            throw new Exception("Could not find user with username: " + username, ex);
        }
    }
}
