package com.nhnacademy.spring.jpa_security.domain.family_relationship;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipRegisterRequest {
    private Long familySerialNumber;
    private String relationShip;
}

