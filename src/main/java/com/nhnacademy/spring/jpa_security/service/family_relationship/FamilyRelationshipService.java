package com.nhnacademy.spring.jpa_security.service.family_relationship;

import com.nhnacademy.spring.jpa_security.domain.family_relationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.family_relationship.FamilyRelationshipRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    FamilyRelationship registerFamilyRelationship(Long serialNumber, FamilyRelationshipRegisterRequest request);

    void updateFamilyRelationship(Long residentSerialNumber, Long familySerialNumber, FamilyRelationshipModifyRequest request);

    void deleteFamilyRelationship(Long residentSerialNumber, Long familySerialNumber);
}
