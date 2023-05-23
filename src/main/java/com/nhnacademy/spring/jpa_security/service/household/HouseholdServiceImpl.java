package com.nhnacademy.spring.jpa_security.service.household;

import com.nhnacademy.spring.jpa_security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.Household;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.exception.AlreadyRegisteredException;
import com.nhnacademy.spring.jpa_security.exception.NotFoundException;
import com.nhnacademy.spring.jpa_security.repository.household.HouseholdRepository;
import com.nhnacademy.spring.jpa_security.repository.household_movement_address.HouseholdMovementAddressRepository;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository,
                                ResidentRepository residentRepository,
                                HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }


    @Override
    public Household registerHousehold(HouseholdRegisterRequest request) {
        Long householdResidentSerialNumber = request.getHouseholdResidentSerialNumber();
        Resident resident = residentRepository.findByResidentSerialNumber(householdResidentSerialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, householdResidentSerialNumber));

        Long householdSerialNumber = request.getHouseholdSerialNumber();
        if(householdRepository.existsByHouseholdSerialNumber(householdSerialNumber)) {
            throw new AlreadyRegisteredException(Household.class, householdSerialNumber);
        }

        Household household = new Household();

        household.setHouseholdSerialNumber(householdSerialNumber);
        household.setHouseholdResident(resident);
        household.setHouseholdCompositionDate(request.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(request.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(request.getCurrentHouseMovementAddress());

        return householdRepository.save(household);
    }

    @Override
    public void deleteHousehold(Long householdSerialNumber) {
        if(!householdRepository.existsByHouseholdSerialNumber(householdSerialNumber)) {
            throw new NotFoundException(Household.class, householdSerialNumber);
        }
        householdMovementAddressRepository.deleteByPk_HouseholdSerialNumber(householdSerialNumber);
        householdRepository.deleteByHouseholdSerialNumber(householdSerialNumber);
    }
}
