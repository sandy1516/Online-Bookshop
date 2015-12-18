package com.san.pro.BookStore.resource;

import com.san.pro.BookStore.model.Book;
import com.san.pro.BookStore.service.BookService;
import io.dropwizard.validation.Validated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sandeepkumar.s on 11/18/2015.
 */
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Books {

    private BookService bookService;

    @Inject
    public Books(BookService bookService) {
        this.bookService = bookService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Validated Book book) {
        bookService.create(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Book book = this.bookService.getById(id);
        return Response.status(Response.Status.OK).entity(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Book> book = bookService.getAll();
        return Response.status(Response.Status.OK).entity(book).build();
    }

    @GET
    @Path("isbn/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByISBN(@PathParam("isbn") String isbn) {
        Book book = bookService.getByISBN(isbn);
        return Response.status(Response.Status.OK).entity(book).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Book model) {
        Book book = bookService.update(id, model);
        return Response.status(Response.Status.OK).entity(book).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        bookService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
