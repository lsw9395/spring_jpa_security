package com.nhnacademy.spring.jpa_security.service.household;

import com.nhnacademy.spring.jpa_security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.Household;

public interface HouseholdService {
    Household registerHousehold(HouseholdRegisterRequest request);

    void deleteHousehold(Long householdSerialNumber);
}
