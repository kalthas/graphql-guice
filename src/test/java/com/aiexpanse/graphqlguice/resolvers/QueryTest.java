package com.aiexpanse.graphqlguice.resolvers;

import com.aiexpanse.graphqlguice.guice.GraphQLModule;
import com.aiexpanse.graphqlguice.guice.GraphQLProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QueryTest {

    Injector injector = Guice.createInjector(new GraphQLModule());

    private Map<String, Object> getInputs(String... contents) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> messages = new ArrayList<>();
        for (String content : contents) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("content", content);
            messages.add(msg);
        }
        map.put("inputs", messages);
        return map;
    }

    @Test
    public void testListInput() {
        GraphQL graphQL = injector.getInstance(GraphQLProvider.class).get();
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query("query q($inputs: [Message!]!) {listInput(inputs: $inputs)}")
                .variables(getInputs("a", "b"))
                .build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        assertEquals(0, executionResult.getErrors().size());
        assertResult(executionResult, "listInput");
    }

    @Test
    public void testListInputAnnotated() {
        GraphQL graphQL = injector.getInstance(GraphQLProvider.class).get();
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query("query q($inputs: [Message!]!) {listInputAnnotated(inputs: $inputs)}")
                .variables(getInputs("a", "b"))
                .build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        assertEquals(0, executionResult.getErrors().size());
        assertResult(executionResult, "listInputAnnotated");
    }

    private void assertResult(ExecutionResult result, String key) {
        assertNotNull(result);
        String listOutput = Optional.of(result.getData())
                .map(LinkedHashMap.class::cast)
                .map(m -> m.get(key))
                .map(String.class::cast)
                .orElse(null);
        assertEquals("a,b", listOutput);
    }

}
