package com.nhnacademy.spring.jpa_security.repository.household_composition_resident;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdCompositionResidentRepositoryTest {
    @Autowired
    HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Test
    void getByHouseholdSerialNumber() {
        List<HouseholdCompositionResidentDto> residents = householdCompositionResidentRepository.getByHouseholdSerialNumber(1L);
        residents.stream().forEach(System.out::println);
    }
}