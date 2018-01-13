package com.aiexpanse.graphqlguice.guice;

import com.aiexpanse.graphqlguice.DoThings;
import com.google.inject.AbstractModule;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

public class GraphQLModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GraphQLProvider.class);
        DoThingsInterceptor doThingsInterceptor = new DoThingsInterceptor();
        requestInjection(doThingsInterceptor);
        bindInterceptor(any(), annotatedWith(DoThings.class), doThingsInterceptor);
    }

}
