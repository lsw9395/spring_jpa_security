package com.nhnacademy.spring.jpa_security.exception;

import com.nhnacademy.spring.jpa_security.entity.BirthDeathReportResident;
import org.junit.jupiter.api.Test;

class AlreadyRegisteredExceptionTest {
    @Test
    void test() {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(1L, "출생");
        Exception exception = new AlreadyRegisteredException(BirthDeathReportResident.class, pk);

        System.out.println(exception.getMessage());
    }

}