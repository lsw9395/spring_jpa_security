package com.nhnacademy.spring.jpa_security.domain.certificate_issue;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CertificateDeathReportDto {
    LocalDate birthDeathReportDate;
    String name;
    String registrationNumber;
    LocalDateTime deathDate;
    String deathPlaceCode;
    String deathPlaceAddress;

    String reportResidentName;
    String reportResidentRegistrationNumber;
    String deathReportQualificationsCode;
    String email;
    String phoneNumber;

    @QueryProjection
    public CertificateDeathReportDto(LocalDate birthDeathReportDate, String name, String registrationNumber, LocalDateTime deathDate, String deathPlaceCode, String deathPlaceAddress, String reportResidentName, String reportResidentRegistrationNumber, String deathReportQualificationsCode, String email, String phoneNumber) {
        this.birthDeathReportDate = birthDeathReportDate;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.deathDate = deathDate;
        this.deathPlaceCode = deathPlaceCode;
        this.deathPlaceAddress = deathPlaceAddress;
        this.reportResidentName = reportResidentName;
        this.reportResidentRegistrationNumber = reportResidentRegistrationNumber;
        this.deathReportQualificationsCode = deathReportQualificationsCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
