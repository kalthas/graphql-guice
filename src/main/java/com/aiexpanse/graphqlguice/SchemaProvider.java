package com.aiexpanse.graphqlguice;

import com.aiexpanse.graphqlguice.resolvers.Query;
import com.coxautodev.graphql.tools.SchemaParser;
import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import graphql.schema.GraphQLSchema;

@Singleton
public class SchemaProvider {

    private final GraphQLSchema schema;

    @Inject
    public SchemaProvider(Query query) {
        SchemaParserOptions options = SchemaParserOptions.newOptions()
                .build();
        schema = SchemaParser.newParser()
                .options(options)
                .file("graphql.schema")
                .resolvers(query)
                .build()
                .makeExecutableSchema();
    }

    public GraphQLSchema get() {
        return schema;
    }

}
