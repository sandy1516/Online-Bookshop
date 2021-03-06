package com.san.pro.BookStore.resource;

import com.google.inject.Inject;
import com.san.pro.BookStore.annotations.AuthParam;
import com.san.pro.BookStore.core.AuthToken;
import com.san.pro.BookStore.model.User;
import com.san.pro.BookStore.service.UserService;
import io.dropwizard.validation.Validated;

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

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Validated User user) {
        userService.create(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(User model) {
        String authToken = userService.login(model);
        return Response.status(Response.Status.OK).entity(authToken).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id, @AuthParam AuthToken authToken) {
        User user = this.userService.getById(id);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@AuthParam AuthToken authToken) {
        List<User> user = userService.getAll();
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, User model, @AuthParam AuthToken authToken) {
        User user = userService.update(id, model);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id, @AuthParam AuthToken authToken) {
        userService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
