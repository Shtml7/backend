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
import model.Team;

/**
 *
 * @author maikel
 * JPA implementation of the Team dao
 */
public class TeamDaoJPA implements TeamDao{
    
    @PersistenceContext(name = "SmartMobilePu")
    private EntityManager entityManager;

    /**
     * Creates a new team
     * @param team The team to be added to the database
     * @return The newly added team
     * @throws Exception When it is not possible to add a team to the database
     */
    @Override
    public Team create(Team team) throws Exception {
        try {
            entityManager.persist(team);
            entityManager.flush();
            return team;
        } catch (Exception ex) {
            throw new Exception("Could not create team", ex);
        }
    }

    /**
     * Gets a team based on the id
     * @param id The id of the team
     * @return The team with given id
     * @throws Exception When it is not possible to find the team with the given id
     */
    @Override
    public Team findById(Long id) throws Exception {
        try {
            return entityManager.find(Team.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find team with id: " + id, ex);
        }
    }

    /**
     * Gets all teams
     * @return A list with all teams
     * @throws Exception When it is not possible to retrieve all teams
     */
    @Override
    public List<Team> findAll() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Team> c = qb.createQuery(Team.class);
            c.from(Team.class);

            TypedQuery<Team> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch(Exception ex) {
            throw new Exception("Could not find teams in database", ex);
        }
    }

    /**
     * Updates an existing team
     * @param team The team to be updated in the database
     * @return The updated game
     * @throws Exception when it is not possible to update the team
     */
    @Override
    public Team update(Team team) throws Exception {
         try {
            entityManager.merge(team);
            return entityManager.find(Team.class, team.getId());
        } catch (Exception ex) {
            throw new Exception("Could not update team in database", ex);
        }
    }
    
}
