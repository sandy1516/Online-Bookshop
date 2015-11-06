package com.san.pro.BookStore.resource;

import com.google.inject.Inject;
import com.san.pro.BookStore.model.User;
import com.san.pro.BookStore.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sandeepkumar.s on 10/28/2015.
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Users {

    private UserService userService;

    @Inject
    public Users(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        userService.create(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        User user = userService.get(id);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<User> user = userService.getAll();
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update() {
        return null;
    }
}
