package com.nhnacademy.spring.jpa_security.domain;

public enum CertificateCode {
    BIRTH("출생신고서"),
    DEATH("사망신고서"),
    REGISTRATION("주민등록등본"),
    RELATION("가족관계증명서");

    private final String value;

    CertificateCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
