package com.san.pro.BookStore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.san.pro.BookStore.config.ApiConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 21-10-2015.
 */
public class Configuration extends io.dropwizard.Configuration{

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public void setDataSourceFactory() {
        this.database = database;
    }

    @Valid
    @NotNull
    @JsonProperty("api")
    private ApiConfiguration apiConfiguration = new ApiConfiguration();

    public ApiConfiguration getApiConfiguration() {
        return apiConfiguration;
    }

    public void setApiConfiguration(ApiConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
    }

    private long tokenExpirationInMillis;

    public long getTokenExpirationInMillis() {
        return tokenExpirationInMillis;
    }

    public void setTokenExpirationInMillis(long tokenExpirationInMillis) {
        this.tokenExpirationInMillis = tokenExpirationInMillis;
    }
}
