package com.nhnacademy.spring.jpa_security.domain.resident;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResidentViewDto {
    String name;
    String residentRegistrationNumber;
    String genderCode;
    int birthReportCode;
    int deathReportCode;

    @QueryProjection
    public ResidentViewDto(String name, String residentRegistrationNumber, String genderCode, int birthReportCode, int deathReportCode) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthReportCode = birthReportCode;
        this.deathReportCode = deathReportCode;
    }


}
