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
public class ResidentFamilyDto {
    String name;
    String registrationNumber;

    @QueryProjection
    public ResidentFamilyDto(String name, String registrationNumber) {
        this.name = name;
        this.registrationNumber = registrationNumber;
    }
}
