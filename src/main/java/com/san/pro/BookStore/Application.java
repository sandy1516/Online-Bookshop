package com.san.pro.BookStore;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Administrator on 20-10-2015.
 */
public class Application extends io.dropwizard.Application<Configuration> {

    private static final String PACKAGE_SCAN_NAME = "com.san.pro";
    private GuiceBundle<Configuration> guiceBundle;
    private BookstoreModule bookstoreModule;

    public static void main(String args) throws Exception{
        new Application().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {

    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bookstoreModule = new BookstoreModule(bootstrap);
        guiceBundle = GuiceBundle.<Configuration>newBuilder()
                .addModule(bookstoreModule)
                .enableAutoConfig(PACKAGE_SCAN_NAME)
                .setConfigClass(Configuration.class)
                .build();
        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new MigrationsBundle<Configuration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(Configuration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

    }
}
