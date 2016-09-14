/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.GameDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.Game;

/**
 *
 * @author maikel
 */
@Stateless
public class GameService {
    
    @Inject
    private GameDao gameDao;
    
    public Game addGame(Game game) throws Exception {
        return gameDao.create(game);
    }

    public Game getGameById(Long id) throws Exception {
        return gameDao.findById(id);
    }

    public List<Game> getAllGames() throws Exception {
        return gameDao.findAll();
    }

    public Game update(Game game) throws Exception {
        return gameDao.update(game);
    }
    
}
