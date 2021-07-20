package com.technical.test.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public <T> EntityNotFoundException(final Class<T> aClass, final String id) {
        super(String.format("Entity %s with ID %s not exit", aClass.getName(), id));
    }
}

