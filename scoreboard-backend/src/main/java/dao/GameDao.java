/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Game;

/**
 *
 * @author maikel
 */
public interface GameDao {
    
    Game create(Game game) throws Exception;

    Game findById(Long id) throws Exception;

    List<Game> findAll() throws Exception;

    Game update(Game game) throws Exception;
}
