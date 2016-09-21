/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import model.User;
import service.UserService;
import util.FileUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

/**
 * @author Eric
 */
@Path("/users")
@Stateless
public class UserAPI {

    @Inject
    private UserService userService;

    public UserAPI() {}

    /**
     * Gets all registered users
     * @return A list with all users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets a user by id
     * @param id The id of the users to be returned
     * @return The user with the given id
     */
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

    /**
     * Creates a new user
     * @param uploadedInputStream The profile image of the new user
     * @param name The name of the new user
     * @param extension The extension of the uploaded image
     * @return The id of the newly created user
     */
    @Path("/upload")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Long upload(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("name") String name, @FormDataParam("extension") String extension) {

        System.out.println("File: " + uploadedInputStream.toString());
        System.out.println("name: " + name);
        System.out.println("ext: " + extension);

        if(!extension.contains(".")) {
            extension = "." + extension;
        }

        User user = new User();
        user.setUsername(name);

        try {
            user = userService.addUser(user);
            String filename = user.getId() + extension;
            FileUtil.writeToFile(uploadedInputStream, filename);
            user.setImageUrl(filename);
            userService.update(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }


        System.out.println("Upload to: " + user.getId() + extension);
        return user.getId();
    }
}
