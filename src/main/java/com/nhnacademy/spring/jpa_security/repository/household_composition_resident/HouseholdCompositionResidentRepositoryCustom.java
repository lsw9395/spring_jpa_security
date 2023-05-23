package com.nhnacademy.spring.jpa_security.repository.household_composition_resident;

import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentDto;
import com.nhnacademy.spring.jpa_security.entity.Resident;

import java.util.List;


public interface HouseholdCompositionResidentRepositoryCustom {
    boolean existsByResident(Resident resident);

    List<HouseholdCompositionResidentDto> getByHouseholdSerialNumber(Long householdSerialNumber);
}
