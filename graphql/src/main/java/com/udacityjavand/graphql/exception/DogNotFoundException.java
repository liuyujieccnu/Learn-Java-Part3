package com.udacityjavand.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String,Object> extensions = new HashMap<>();//extension可以返回额外的数据到客户端对象

    public DogNotFoundException(String message,Long invalidDogId){
        super(message);
        extensions.put("invalidDogID",invalidDogId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String,Object> getExtensions(){
        return extensions;
    }
}
