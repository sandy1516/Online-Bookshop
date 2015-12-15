package com.san.pro.BookStore.exceptions.mapper;

import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by sandeepkumar.s on 12/15/2015.
 */
@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException apiException) {
        // perform logging here and return status
        if(apiException.getCause() != null) {
            apiException.getCause().printStackTrace();
        }
        if(apiException.getErrors() != null) {
            return Response.status(apiException.getStatus()).entity(apiException.getErrors()).build();
        } else {
            return Response.status(apiException.getStatus() != null ? apiException.getStatus() : Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorCodes.INTERNAL_SERVER_ERROR).build();
        }
    }
}
