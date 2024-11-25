package org.example.videoservice.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable exception, DataFetchingEnvironment environment) {
        return new GraphQLError(){
            @Override
            public String getMessage() {
                return exception.getMessage();
            }
            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }
            @Override
            public ErrorClassification getErrorType() {
                return null;
            }
        };
    }
}
