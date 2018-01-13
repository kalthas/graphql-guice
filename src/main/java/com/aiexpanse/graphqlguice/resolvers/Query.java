package com.aiexpanse.graphqlguice.resolvers;

import com.aiexpanse.graphqlguice.DoThings;
import com.aiexpanse.graphqlguice.types.Message;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class Query implements GraphQLQueryResolver {

    //listInput(inputs: [Message!]!): String
    public String listInput(List<Message> inputs) {
        return String.join(",", inputs.stream().map(Message::getContent).collect(Collectors.toList()));
    }

    //listInputAnnotated(inputs: [Message!]!): String
    @DoThings
    public String listInputAnnotated(List<Message> inputs) {
        return String.join(",", inputs.stream().map(Message::getContent).collect(Collectors.toList()));
    }

}
