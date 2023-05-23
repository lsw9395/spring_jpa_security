package com.nhnacademy.spring.jpa_security.domain.certificate_issue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRelationDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CertificateFamilyRelationshipDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate certificateIssueDate;
    Long certificateConfirmationNumber;
    String registrationAddress;
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime birthDate;
    String registrationNumber;
    String genderCode;

    List<ResidentRelationDto> family;

    @QueryProjection
    public CertificateFamilyRelationshipDto(LocalDate certificateIssueDate,
                                            Long certificateConfirmationNumber,
                                            String registrationAddress,
                                            String name,
                                            LocalDateTime birthDate,
                                            String registrationNumber,
                                            String genderCode) {
        this.certificateIssueDate = certificateIssueDate;
        this.certificateConfirmationNumber = certificateConfirmationNumber;
        this.registrationAddress = registrationAddress;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationNumber = registrationNumber;
        this.genderCode = genderCode;
    }
}
