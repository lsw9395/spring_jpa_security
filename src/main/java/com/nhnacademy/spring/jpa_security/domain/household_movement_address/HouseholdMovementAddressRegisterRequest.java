package com.nhnacademy.spring.jpa_security.domain.household_movement_address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdMovementAddressRegisterRequest {
    LocalDate reportDate;
    String address;
    String lastAddressYn;
}
