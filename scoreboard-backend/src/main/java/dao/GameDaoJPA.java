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
 * JPA implementation of the Game dao
 */
public class GameDaoJPA implements GameDao{
    
    @PersistenceContext(name = "SmartMobilePu")
    private EntityManager entityManager;

    /**
     * Creates a new game
     * @param game The game to be added in the database
     * @return The added game in the databse
     * @throws Exception When it is not possible to add the game to the database
     */
    @Override
    public Game create(Game game) throws Exception {
        try {
            entityManager.persist(game);
            entityManager.flush();
            return game;
        } catch (Exception ex) {
            throw new Exception("Could not add game to databse", ex);
        }
    }

    /**
     * Gets an game with the given id
     * @param id The id of the game
     * @return The game with the given id
     * @throws Exception When it is not possible to find the game
     */
    @Override
    public Game findById(Long id) throws Exception {
        try {
            return entityManager.find(Game.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find game with id: " + id, ex);
        }
    }

    /**
     * Gets all games
     * @return A list of all games known in the database
     * @throws Exception When it is not possible to retrieve the games
     */
    @Override
    public List<Game> findAll() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Game> c = qb.createQuery(Game.class);
            c.from(Game.class);

            TypedQuery<Game> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch(Exception ex) {
            throw new Exception("Could not find games in database", ex);
        }
    }

    /**
     * Updates an existing game
     * @param game The game to be updated in the database
     * @return The updated game
     * @throws Exception When it is not possible to update the game
     */
    @Override
    public Game update(Game game) throws Exception {
         try {
            entityManager.merge(game);
            return entityManager.find(Game.class, game.getId());
        } catch (Exception ex) {
            throw new Exception("Could not update game in database", ex);
        }
    }
}
