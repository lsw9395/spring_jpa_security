package com.nhnacademy.spring.jpa_security.service.family_relationship;

import com.nhnacademy.spring.jpa_security.domain.QualificationCode;
import com.nhnacademy.spring.jpa_security.domain.family_relationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.family_relationship.FamilyRelationshipRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.FamilyRelationship;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.exception.AlreadyRegisteredException;
import com.nhnacademy.spring.jpa_security.exception.NotFoundException;
import com.nhnacademy.spring.jpa_security.repository.family_relationship.FamilyRelationshipRepository;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;

    private final ResidentRepository residentRepository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public FamilyRelationship registerFamilyRelationship(Long serialNumber, FamilyRelationshipRegisterRequest request) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, serialNumber));

        Long familySerialNumber = request.getFamilySerialNumber();
        Resident familyResident = residentRepository.findByResidentSerialNumber(familySerialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, familySerialNumber));

        FamilyRelationship.Pk pk = getExistFamilyRelationshipPk(serialNumber, request.getFamilySerialNumber());

        FamilyRelationship familyRelationship = getFamilyRelationship(request, resident, familyResident, pk);

        return familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    public void updateFamilyRelationship(Long residentSerialNumber, Long familySerialNumber, FamilyRelationshipModifyRequest request) {
        FamilyRelationship.Pk pk = getFamilyRelationshipPk(residentSerialNumber, familySerialNumber);

        String relationship = QualificationCode.valueOf(request.getRelationShip().toUpperCase()).toString();

        familyRelationshipRepository.updateByPk(pk, relationship);
    }

    @Override
    public void deleteFamilyRelationship(Long residentSerialNumber, Long familySerialNumber) {
        FamilyRelationship.Pk pk = getFamilyRelationshipPk(residentSerialNumber, familySerialNumber);

        familyRelationshipRepository.deleteById(pk);
    }

    private FamilyRelationship getFamilyRelationship(FamilyRelationshipRegisterRequest request, Resident resident, Resident familyResident, FamilyRelationship.Pk pk) {
        String relationship = QualificationCode.valueOf(request.getRelationShip().toUpperCase()).toString();

        FamilyRelationship familyRelationship = new FamilyRelationship();

        familyRelationship.setPk(pk);
        familyRelationship.setFamilyRelationshipCode(relationship);
        familyRelationship.setResident(resident);
        familyRelationship.setFamilyResident(familyResident);

        return familyRelationship;
    }

    private FamilyRelationship.Pk getExistFamilyRelationshipPk(Long serialNumber, Long familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(serialNumber, familySerialNumber);

        if (familyRelationshipRepository.existsByPk(pk)) {
            throw new AlreadyRegisteredException(FamilyRelationship.class, pk);
        }
        return pk;
    }

    private FamilyRelationship.Pk getFamilyRelationshipPk(Long residentSerialNumber, Long familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(residentSerialNumber, familySerialNumber);

        if (!familyRelationshipRepository.existsByPk(pk)) {
            throw new NotFoundException(FamilyRelationship.class, pk);
        }
        return pk;
    }
}
