package com.nhnacademy.spring.jpa_security.service.household_composition_resident;

import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.Household;
import com.nhnacademy.spring.jpa_security.entity.HouseholdCompositionResident;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.exception.AlreadyRegisteredException;
import com.nhnacademy.spring.jpa_security.exception.NotFoundException;
import com.nhnacademy.spring.jpa_security.repository.household.HouseholdRepository;
import com.nhnacademy.spring.jpa_security.repository.household_composition_resident.HouseholdCompositionResidentRepository;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseholdCompositionResidentServiceImpl implements HouseholdCompositionResidentService {
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdCompositionResidentServiceImpl(HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                                                   HouseholdRepository householdRepository,
                                                   ResidentRepository residentRepository) {
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }


    @Override
    public HouseholdCompositionResident registerHouseholdCompositionResident(Long householdSerialNumber,
                                                                             HouseholdCompositionResidentRegisterRequest request) {
        Household household = householdRepository.findByHouseholdSerialNumber(householdSerialNumber)
                .orElseThrow(() -> new NotFoundException(Household.class, householdSerialNumber));

        Long residentSerialNumber = request.getResidentSerialNumber();
        Resident resident = residentRepository.findByResidentSerialNumber(residentSerialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, residentSerialNumber));

        HouseholdCompositionResident.Pk pk = getHouseholdCompositionResident(householdSerialNumber, residentSerialNumber);

        HouseholdCompositionResident householdCompositionResident = getHouseholdCompositionResidentWith(request, household, resident, pk);

        return householdCompositionResidentRepository.save(householdCompositionResident);
    }

    @Override
    public void deleteHouseholdCompositionResident(Long householdSerialNumber, Long residentSerialNumber) {
        HouseholdCompositionResident.Pk pk = getExistHouseholdCompositionResident(householdSerialNumber, residentSerialNumber);

        householdCompositionResidentRepository.deleteByPk(pk);
    }

    private HouseholdCompositionResident getHouseholdCompositionResidentWith(HouseholdCompositionResidentRegisterRequest request, Household household, Resident resident, HouseholdCompositionResident.Pk pk) {
        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();

        householdCompositionResident.setPk(pk);
        householdCompositionResident.setReportDate(request.getReportDate());
        householdCompositionResident.setHouseholdRelationshipCode(request.getRelationshipCode());
        householdCompositionResident.setHouseholdCompositionChangeReasonCode(request.getCompositionChangeReasonCode());
        householdCompositionResident.setResident(resident);
        householdCompositionResident.setHousehold(household);
        return householdCompositionResident;
    }

    private HouseholdCompositionResident.Pk getHouseholdCompositionResident(Long householdSerialNumber, Long residentSerialNumber) {
        HouseholdCompositionResident.Pk pk = new HouseholdCompositionResident.Pk(householdSerialNumber, residentSerialNumber);

        if (householdCompositionResidentRepository.existsByPk(pk)) {
            throw new AlreadyRegisteredException(HouseholdCompositionResident.class, pk);
        }
        return pk;
    }

    private HouseholdCompositionResident.Pk getExistHouseholdCompositionResident(Long householdSerialNumber, Long residentSerialNumber) {
        HouseholdCompositionResident.Pk pk = new HouseholdCompositionResident.Pk(householdSerialNumber, residentSerialNumber);

        if (!householdCompositionResidentRepository.existsByPk(pk)) {
            throw new NotFoundException(HouseholdCompositionResident.class, pk);
        }
        return pk;
    }
}
