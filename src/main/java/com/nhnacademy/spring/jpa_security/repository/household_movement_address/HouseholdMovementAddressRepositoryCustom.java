package com.nhnacademy.spring.jpa_security.repository.household_movement_address;

import com.nhnacademy.spring.jpa_security.domain.household_movement_address.HouseholdMovementAddressDto;

import java.util.List;

public interface HouseholdMovementAddressRepositoryCustom  {
    List<HouseholdMovementAddressDto> getByHouseholdSerialNumber(Long householdSerialNumber);

}
