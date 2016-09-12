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
 */
public class TeamDaoJPA implements TeamDao{
    
    @PersistenceContext(name = "SmartMobilePu")
    private EntityManager entityManager;

    @Override
    public Team create(Team team) throws Exception {
        try {
            entityManager.persist(team);
            entityManager.flush();
            return team;
        } catch (Exception ex) {
            throw new Exception("Could not create user", ex);
        }
    }

    @Override
    public Team findById(Long id) throws Exception {
        try {
            return entityManager.find(Team.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find user with id: " + id, ex);
        }
    }

    @Override
    public List<Team> findAll() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Team> c = qb.createQuery(Team.class);
            c.from(Team.class);

            TypedQuery<Team> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch(Exception ex) {
            throw new Exception("Could not find users in database", ex);
        }
    }

    @Override
    public Team update(Team team) throws Exception {
         try {
            entityManager.merge(team);
            return entityManager.find(Team.class, team.getId());
        } catch (Exception ex) {
            throw new Exception("Could not update user in database", ex);
        }
    }
    
}
