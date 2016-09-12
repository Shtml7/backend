/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import model.Game;

/**
 *
 * @author maikel
 */
public class GameDaoJPA implements GameDao{
    
    @PersistenceContext(name = "SmartMobilePu")
    private EntityManager entityManager;

    @Override
    public Game create(Game game) throws Exception {
        try {
            entityManager.persist(game);
            entityManager.flush();
            return game;
        } catch (Exception ex) {
            throw new Exception("Could not create user", ex);
        }
    }

    @Override
    public Game findById(Long id) throws Exception {
        try {
            return entityManager.find(Game.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find user with id: " + id, ex);
        }
    }

    @Override
    public List<Game> findAll() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Game> c = qb.createQuery(Game.class);
            c.from(Game.class);

            TypedQuery<Game> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch(Exception ex) {
            throw new Exception("Could not find users in database", ex);
        }
    }

    @Override
    public Game update(Game game) throws Exception {
         try {
            entityManager.merge(game);
            return entityManager.find(Game.class, game.getId());
        } catch (Exception ex) {
            throw new Exception("Could not update user in database", ex);
        }
    }
    
}
