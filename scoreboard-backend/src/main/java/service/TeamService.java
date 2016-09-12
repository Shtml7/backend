/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TeamDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Team;

/**
 *
 * @author maikel
 */
@Stateless
public class TeamService {
    
    @Inject
    private TeamDao teamDao;
    
    public Team addUser(Team team) throws Exception {
        return teamDao.create(team);
    }

    public Team getUserById(Long id) throws Exception {
        return teamDao.findById(id);
    }

    public List<Team> getAllGames() throws Exception {
        return teamDao.findAll();
    }

    public Team update(Team team) throws Exception {
        return teamDao.update(team);
    }
    
}
