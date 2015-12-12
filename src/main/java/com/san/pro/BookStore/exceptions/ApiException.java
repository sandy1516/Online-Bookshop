package com.san.pro.BookStore.exceptions;

import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;

/**
 * Created by sandeepkumar.s on 12/12/2015.
 */
public class ApiException extends WebServiceException {
    public Response.Status status;
}
