package com.nhnacademy.spring.jpa_security.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String emailAddress) {
        super("Login Failed Exception : Not Registered EmailAddress" + emailAddress);
    }
}
