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

    /**
     * Creates a new user
     * @param user The user to be added to the database
     * @return The newly added user
     * @throws Exception When it is not possible to add the user to the database
     */
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

    /**
     * Gets an user with the given id
     * @param id The id of the user
     * @return The user with the given id
     * @throws Exception When it is not possible to find the user with id
     */
    @Override
    public User findById(Long id) throws Exception {
        try {
            return entityManager.find(User.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find user with id: " + id, ex);
        }
    }

    /**
     * Gets all users known in the database
     * @return A list with users
     * @throws Exception When it is not possible to get the users
     */
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

    /**
     * Updates an existing user
     * @param user The user to be updated in the database
     * @return The updated user
     * @throws Exception When it is not possible to update the user
     */
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
