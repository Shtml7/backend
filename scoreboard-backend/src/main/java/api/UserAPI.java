/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import model.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Eric
 */
@Path("/users")
@Stateless
public class UserAPI {

    @Inject
    private UserService userService;
    
    public UserAPI(){}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(User user){
        if (user.getUsername().isEmpty()) {
            throw new WebApplicationException("Invalid request", Response.Status.BAD_REQUEST);
        }
        try {
            return userService.addUser(user);
        } catch(Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") Long id) {
        try {
            return userService.getUserById(id);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("/username={username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@PathParam("username") String username) {
        try {
            return userService.getUserByUsername(username);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.NOT_FOUND);
        }
    }


    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "{\"name\":\"kees\"}";
    }
    
}
