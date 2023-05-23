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
public class ResidentListDto {
    Long residentSerialNumber;
    String name;
    String genderCode;
    String residentRegistrationNumber;
    String registrationBaseAddress;

    @QueryProjection
    public ResidentListDto(Long residentSerialNumber, String name, String genderCode, String residentRegistrationNumber, String registrationBaseAddress) {
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        this.genderCode = genderCode;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.registrationBaseAddress = registrationBaseAddress;
    }
}
