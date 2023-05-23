package com.nhnacademy.spring.jpa_security.repository.family_relationship;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.entity.FamilyRelationship;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class FamilyRelationshipRepositoryTest {

    @Autowired
    FamilyRelationshipRepository familyRelationshipRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Test
    void updateById() {
        Long residentSerialNumber = 2L;
        Long familySerialNumber = 1L;

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(residentSerialNumber, familySerialNumber);


        familyRelationshipRepository.updateByPk(pk, "sister");
    }

}