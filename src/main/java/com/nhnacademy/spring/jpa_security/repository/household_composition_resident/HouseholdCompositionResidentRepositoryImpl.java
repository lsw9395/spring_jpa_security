package com.nhnacademy.spring.jpa_security.repository.household_composition_resident;

import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentDto;
import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.QHouseholdCompositionResidentDto;
import com.nhnacademy.spring.jpa_security.entity.HouseholdCompositionResident;
import com.nhnacademy.spring.jpa_security.entity.QHouseholdCompositionResident;
import com.nhnacademy.spring.jpa_security.entity.QResident;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport implements HouseholdCompositionResidentRepositoryCustom {

    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }

    @Override
    public boolean existsByResident(Resident resident) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;

        long count = from(householdCompositionResident)
                .where(householdCompositionResident.household.householdResident.eq(resident)
                        .or(householdCompositionResident.resident.eq(resident)))
                .select(householdCompositionResident.count())
                .fetchCount();
        return count > 0;
    }

    @Override
    public List<HouseholdCompositionResidentDto> getByHouseholdSerialNumber(Long householdSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QResident resident = QResident.resident;

        return from(householdCompositionResident)
                .rightJoin(householdCompositionResident.resident, resident)
                .select(new QHouseholdCompositionResidentDto(
                        householdCompositionResident.householdRelationshipCode,
                        resident.name,
                        resident.residentRegistrationNumber,
                        householdCompositionResident.reportDate,
                        householdCompositionResident.householdCompositionChangeReasonCode
                ))
                .where(householdCompositionResident.household.householdSerialNumber.eq(householdSerialNumber))
                .fetch();
    }
}
