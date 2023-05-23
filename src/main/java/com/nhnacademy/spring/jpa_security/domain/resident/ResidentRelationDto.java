package com.nhnacademy.spring.jpa_security.domain.resident;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResidentRelationDto {
    String role;
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime birthDate;
    String registrationNumber;
    String genderCode;

    @QueryProjection
    public ResidentRelationDto(String role, String name, LocalDateTime birthDate, String registrationNumber, String genderCode) {
        this.role = role;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationNumber = registrationNumber;
        this.genderCode = genderCode;
    }
}
