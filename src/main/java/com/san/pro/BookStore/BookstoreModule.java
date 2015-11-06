package com.san.pro.BookStore;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.skife.jdbi.v2.DBI;


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

    @Override
    protected void configure() {
    }
}
