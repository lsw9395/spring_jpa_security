package com.nhnacademy.spring.jpa_security.service.household_movement_address;

import com.nhnacademy.spring.jpa_security.domain.household_movement_address.HouseholdMovementAddressModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.household_movement_address.HouseholdMovementAddressRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.HouseholdMovementAddress;

import java.time.LocalDate;

public interface HouseholdMovementAddressService {
    HouseholdMovementAddress registerHouseholdMovementAddress(Long householdSerialNumber, HouseholdMovementAddressRegisterRequest request);

    void updateHouseholdMovementAddress(Long householdSerialNumber, LocalDate reportDate, HouseholdMovementAddressModifyRequest request);

    void deleteHouseholdMovementAddress(Long householdSerialNumber, LocalDate reportDate);
}
