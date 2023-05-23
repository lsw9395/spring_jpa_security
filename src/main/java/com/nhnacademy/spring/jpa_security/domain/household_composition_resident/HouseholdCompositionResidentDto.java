package com.nhnacademy.spring.jpa_security.domain.household_composition_resident;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HouseholdCompositionResidentDto {
    String householdRelationshipCode;
    String name;
    String registrationNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate reportDate;
    String householdCompositionChangeReasonCode;

    @QueryProjection
    public HouseholdCompositionResidentDto(String householdRelationshipCode, String name, String registrationNumber, LocalDate reportDate, String householdCompositionChangeReasonCode) {
        this.householdRelationshipCode = householdRelationshipCode;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.reportDate = reportDate;
        this.householdCompositionChangeReasonCode = householdCompositionChangeReasonCode;
    }
}
