package com.nhnacademy.spring.jpa_security.exception;

public class ResidentDeleteFailedException extends RuntimeException {
    public ResidentDeleteFailedException() {
        super("남은 가족이 있어 삭제가 불가능합니다.");
    }
}
