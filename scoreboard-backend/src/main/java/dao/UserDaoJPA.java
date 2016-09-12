package dao;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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
            entityManager.flush();
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

    @Override
    public List<User> findAll() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> c = qb.createQuery(User.class);
            c.from(User.class);

            TypedQuery<User> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch(Exception ex) {
            throw new Exception("Could not find users in database", ex);
        }
    }

    @Override
    public User update(User user) throws Exception {
        try {
            entityManager.merge(user);
            return entityManager.find(User.class, user.getId());
        } catch (Exception ex) {
            throw new Exception("Could not update user in database", ex);
        }
    }
}
