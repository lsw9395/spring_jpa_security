package com.nhnacademy.spring.jpa_security.domain;

public enum QualificationCode {
    FATHER("부"),
    MOTHER("모"),
    SPOUSE("배우자"),
    CHILD("자녀");

    private final String value;
    QualificationCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
