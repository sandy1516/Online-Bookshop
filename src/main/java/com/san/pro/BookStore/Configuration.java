package com.san.pro.BookStore;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
