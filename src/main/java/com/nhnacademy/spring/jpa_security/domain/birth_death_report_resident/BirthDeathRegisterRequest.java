package com.nhnacademy.spring.jpa_security.domain.birth_death_report_resident;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathRegisterRequest {
    Long residentSerialNumber;
    LocalDate reportDate;
    String reportQualificationsCode;
    String emailAddress;
    String phoneNumber;
}
