package com.san.pro.BookStore;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Administrator on 20-10-2015.
 */
public class Application extends io.dropwizard.Application<Configuration> {

    private static final String PACKAGE_SCAN_NAME = "com.san.pro";
    private GuiceBundle<Configuration> guiceBundle;
    private BookstoreModule bookstoreModule = new BookstoreModule();

    public static void main(String[] args) throws Exception{
        new Application().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        try {
            final DBIFactory factory = new DBIFactory();
            final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
            bookstoreModule.setDbi(jdbi);
        } catch (NoClassDefFoundError ex) {

        }
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
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
