package com.san.pro.BookStore.providers;

import com.san.pro.BookStore.core.AuthToken;
import com.san.pro.BookStore.factories.AuthTokenValueFactory;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.model.Parameter;

import javax.ws.rs.ext.Provider;

/**
 * Created by sandeepkumar.s on 11/27/2015.
 */
@Provider
public class AuthParamValueFactoryProvider extends AbstractValueFactoryProvider {

    protected AuthParamValueFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator, Parameter.Source... compatibleSources) {
        super(mpep, locator, compatibleSources);
    }

    @Override
    protected Factory<?> createValueFactory(Parameter parameter) {
        Class<?> classType =  parameter.getRawType();
        if(classType == null || (classType.equals(AuthToken.class))) {
            return null;
        }
        return new AuthTokenValueFactory();
    }

}
