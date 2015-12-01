package com.san.pro.BookStore.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.inject.Singleton;

/**
 * Created by sandeepkumar.s on 11/30/2015.
 */
@Singleton
public class ApiConfiguration {

    public static final String NAMED_BINDING = "com.san.pro.BookStore.config";

    @NotBlank
    @JsonProperty
    private String jwtAudience;

    @NotBlank
    @JsonProperty
    private String jwtSecret;

    public String getJwtAudience() {
        return jwtAudience;
    }

    public void setJwtAudience(String jwtAudience) {
        this.jwtAudience = jwtAudience;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
}
