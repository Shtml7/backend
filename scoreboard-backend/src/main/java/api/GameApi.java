/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.List;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllUsers() {
        try {
            return gameService.getAllGames();
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
