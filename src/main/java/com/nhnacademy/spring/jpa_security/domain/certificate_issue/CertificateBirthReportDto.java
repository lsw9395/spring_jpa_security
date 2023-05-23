package com.nhnacademy.spring.jpa_security.domain.certificate_issue;

import com.nhnacademy.spring.jpa_security.domain.resident.ResidentFamilyDto;
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
public class CertificateBirthReportDto {
    LocalDate birthDeathReportDate;
    String name;
    String genderCode;
    LocalDateTime birthDate;
    String birthPlaceCode;
    String registrationBaseAddress;

    String reportResidentName;
    String reportResidentRegistrationNumber;
    String birthReportQualificationsCode;
    String email;
    String phoneNumber;

    ResidentFamilyDto father;
    ResidentFamilyDto mother;

    @QueryProjection
    public CertificateBirthReportDto(LocalDate birthDeathReportDate,
                                     String name, String genderCode,
                                     LocalDateTime birthDate,
                                     String birthPlaceCode,
                                     String registrationBaseAddress,
                                     String reportResidentName,
                                     String reportResidentRegistrationNumber,
                                     String birthReportQualificationsCode,
                                     String email, String phoneNumber) {
        this.birthDeathReportDate = birthDeathReportDate;
        this.name = name;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
        this.reportResidentName = reportResidentName;
        this.reportResidentRegistrationNumber = reportResidentRegistrationNumber;
        this.birthReportQualificationsCode = birthReportQualificationsCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
