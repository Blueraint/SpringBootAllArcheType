package com.spring.SpringbootAllArchetype.core.exception;

public class MyServiceException extends RuntimeException {
    public MyServiceException(String message) {
        super(message);
    }
}
