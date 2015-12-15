package com.san.pro.BookStore.exceptions;

import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeepkumar.s on 12/12/2015.
 */
public class ApiException extends WebServiceException {

    private Response.Status status;
    private List<ErrorCodes> errors;

    public ApiException(Throwable cause, Response.Status status) {
        super(cause);
        this.status = status;
    }

    public ApiException(Throwable cause, Response.Status status, List<ErrorCodes> errors) {
        super(cause);
        this.status = status;
        this.errors = errors;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public List<ErrorCodes> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorCodes> errors) {
        this.errors = errors;
    }

    public ApiException addError(ErrorCodes error) {
        if(this.getErrors() == null) {
            this.setErrors(new ArrayList<ErrorCodes>());
        }
        this.getErrors().add(error);
        return this;

    }
}
