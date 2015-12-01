package com.san.pro.BookStore;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.san.pro.BookStore.config.ApiConfiguration;
import org.skife.jdbi.v2.DBI;

import javax.inject.Named;


/**
 * Created by Administrator on 21-10-2015.
 */
public class BookstoreModule extends AbstractModule {

    private static DBI dbi;

    @Provides
    public static DBI getDbi() {
        return dbi;
    }

    public static void setDbi(DBI dbi) {
        BookstoreModule.dbi = dbi;
    }

    @Provides
    @Named(ApiConfiguration.NAMED_BINDING)
    public ApiConfiguration getApiConfiguration(Configuration configuration) {
        return configuration.getApiConfiguration();
    }

    @Override
    protected void configure() {
    }
}
