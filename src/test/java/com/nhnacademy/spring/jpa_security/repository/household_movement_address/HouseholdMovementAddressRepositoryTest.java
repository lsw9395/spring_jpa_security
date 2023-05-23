package com.nhnacademy.spring.jpa_security.repository.household_movement_address;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.domain.household_movement_address.HouseholdMovementAddressDto;
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
class HouseholdMovementAddressRepositoryTest {

    @Autowired
    HouseholdMovementAddressRepository householdMovementAddressRepository;

    @Test
    void getByHouseholdSerialNumber() {
        List<HouseholdMovementAddressDto> address = householdMovementAddressRepository.getByHouseholdSerialNumber(1L);
        address.stream().forEach(System.out::println);
    }
}