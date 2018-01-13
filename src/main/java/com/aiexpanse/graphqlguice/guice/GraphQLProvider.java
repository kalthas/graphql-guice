package com.aiexpanse.graphqlguice.guice;

import com.aiexpanse.graphqlguice.SchemaProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

@Singleton
public class GraphQLProvider {

    @Inject
    private SchemaProvider provider;

    public GraphQL get() {
        GraphQLSchema schema = provider.get();
        return GraphQL.newGraphQL(schema)
                .build();
    }

}
