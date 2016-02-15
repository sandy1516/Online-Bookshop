package com.san.pro.BookStore.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by sandeepkumar.s on 2/13/2016.
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter{
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders()
                .add("Access-Control-Allow-Origin", "*");
        containerResponseContext.getHeaders()
                .add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        containerResponseContext.getHeaders()
                .add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
    }
}