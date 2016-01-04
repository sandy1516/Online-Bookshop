package com.san.pro.BookStore.resource;

import com.san.pro.BookStore.model.Order;
import com.san.pro.BookStore.service.BookService;
import com.san.pro.BookStore.service.OrderService;
import io.dropwizard.validation.Validated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sandeepkumar.s on 12/23/2015.
 */
@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Orders {

    private OrderService orderService;
    private BookService bookService;

    @Inject
    public Orders(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Validated Order order) {
        orderService.create(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Order order = this.orderService.getById(id);
        return Response.status(Response.Status.OK).entity(order).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Order> order = orderService.getAll();
        return Response.status(Response.Status.OK).entity(order).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Order model) {
        Order order = orderService.update(id, model);
        return Response.status(Response.Status.OK).entity(order).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        orderService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
