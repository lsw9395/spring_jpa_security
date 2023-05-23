package com.nhnacademy.spring.jpa_security.exception;

public class NotIssuedCertificateException extends RuntimeException {
    public NotIssuedCertificateException(String certificateType) {
        super(getCauseMessage(certificateType));
    }

    private static String getCauseMessage(String certificateType) {
        switch (certificateType) {
            case "주민등록등본" :
                return "세대주가 아니거나 데이터가 존재하지않습니다.";
        }
        return "Unknown";
    }
}
