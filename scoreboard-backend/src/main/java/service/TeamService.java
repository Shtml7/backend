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
    
    public Team addTeam(Team team) throws Exception {
        return teamDao.create(team);
    }

    public Team getTeamById(Long id) throws Exception {
        return teamDao.findById(id);
    }

    public List<Team> getAllTeam() throws Exception {
        return teamDao.findAll();
    }

    public Team update(Team team) throws Exception {
        return teamDao.update(team);
    }
    
}
