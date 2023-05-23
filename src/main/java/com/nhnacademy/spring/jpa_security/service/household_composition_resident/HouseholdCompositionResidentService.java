package com.nhnacademy.spring.jpa_security.service.household_composition_resident;

import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.HouseholdCompositionResident;

public interface HouseholdCompositionResidentService {

    HouseholdCompositionResident registerHouseholdCompositionResident(Long householdSerialNumber, HouseholdCompositionResidentRegisterRequest request);

    void deleteHouseholdCompositionResident(Long householdSerialNumber, Long residentSerialNumber);
}
