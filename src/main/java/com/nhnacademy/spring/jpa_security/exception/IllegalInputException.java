package com.nhnacademy.spring.jpa_security.exception;

public class IllegalInputException extends RuntimeException {
    public IllegalInputException(String code, Object object) {
        super(new StringBuilder()
                .append("Illegal Input : ")
                .append(getCauseByCode(code))
                .append(" ( Resident : ")
                .append(object)
                .append(" )")
                .toString());
    }
    private static String getCauseByCode(String code) {
        switch (code) {
            case "self" :
                return "Can't Register Birth/Death Report by Self";
        }
        return "none";
    }
}
