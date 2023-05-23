package com.nhnacademy.spring.jpa_security.domain.household;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdRegisterRequest {
    Long householdSerialNumber;
    Long householdResidentSerialNumber;
    LocalDate householdCompositionDate;
    String householdCompositionReasonCode;
    String currentHouseMovementAddress;
}
