package com.nhnacademy.spring.jpa_security.exception;

public class InvalidAccessCodeException extends RuntimeException {
    public InvalidAccessCodeException(String accessToken) {
        super("Invalid Access Token :" + accessToken);
    }
}
