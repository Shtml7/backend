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

/**
 * @author Eric
 */
@Path("/users")
@Stateless
public class UserAPI {

    @Inject
    private UserService userService;

    public UserAPI() {
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


    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "{\"name\":\"kees\"}";
    }


    @Path("/upload")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String upload(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("name") String name, @FormDataParam("extension") String extension) {

        // Path format //10.217.14.97/Installables/uploaded/
        System.out.println("File: " + uploadedInputStream.toString());
        System.out.println("name: " + name);
        System.out.println("ext: " + extension);

        if(!extension.contains(".")) {
            extension = "." + extension;
        }

        String filename = name + extension;

        User user = new User();
        user.setUsername(name);
        user.setImageUrl(filename);

        try {
            userService.addUser(user);
            FileUtil.writeToFile(uploadedInputStream, filename);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WebApplicationException(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }


        System.out.println("Upload to: " + filename);
        return filename;
    }

}
