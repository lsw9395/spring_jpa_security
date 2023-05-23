package com.nhnacademy.spring.jpa_security.domain;

public enum BirthDeathCode {
    BIRTH("출생"),
    DEATH("사망");

    private final String value;

    BirthDeathCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
