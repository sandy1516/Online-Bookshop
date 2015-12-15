package com.san.pro.BookStore.exceptions;

/**
 * Created by sandeepkumar.s on 12/14/2015.
 */
public enum ErrorCodes {

    MANDATORY_FIELD_MISSING(400001, " One of the mandatory field is missing"),
    EMAIL_ALREADY_EXIST(400002, "Email already exist"),

    UNAUTHORIZED(401001, " User is not authorized "),
    JWT_TOKEN_EXPIRED(401002, " JWT Token expired, Kindly refresh the token by login again"),
    INVALID_AUTHORIZATION_TOKEN(401003, "Invalid authorization token"),
    INVALID_VERIFICATION_TOKEN(401004, "Invalid verification token"),
    INVALID_PASSWORD(401005, "Invalid password"),

    INTERNAL_SERVER_ERROR(500001, "Internal server error occured while processing your request"),
    RESOURCE_NOT_FOUND(404001, "Requested resource not found");

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
