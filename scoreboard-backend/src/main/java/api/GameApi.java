/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import service.GameService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Game;

/**
 *
 * @author maikel
 */
@Path("/games")
@Stateless
public class GameApi {

    @Inject
    private GameService gameService;

    public GameApi() {
    }

    /**
     * Get all known games
     * @return A list with all games
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames() {
        try {
            return gameService.getAllGames();
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new Game
     * @param game The new game to be added to the database
     * @return The newly added game if successful, otherwise an exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Game create(Game game) {
        try {
            return gameService.addGame(game);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing game e.g. when the score changes
     * @param game The new game
     * @return The updated game from the database if successful
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Game update(Game game) {
        try {
            return gameService.update(game);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets a game by id
     * @param id The id of the game
     * @return The game with the given id
     */
    @Path("/{gameId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameById(@PathParam("gameId") Long id){
        try {
            return gameService.getGameById(id);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
