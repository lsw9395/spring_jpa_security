package com.nhnacademy.spring.jpa_security.domain.household_composition_resident;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdCompositionResidentRegisterRequest {
    Long residentSerialNumber;
    LocalDate reportDate;
    String relationshipCode;
    String compositionChangeReasonCode;
}
