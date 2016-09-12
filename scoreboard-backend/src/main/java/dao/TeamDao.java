/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Team;

/**
 *
 * @author maikel
 */
public interface TeamDao {
    
    Team create(Team team) throws Exception;

    Team findById(Long id) throws Exception;

    List<Team> findAll() throws Exception;

    Team update(Team team) throws Exception;
}
