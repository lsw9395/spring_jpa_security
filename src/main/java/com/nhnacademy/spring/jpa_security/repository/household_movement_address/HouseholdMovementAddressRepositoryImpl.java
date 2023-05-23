package com.nhnacademy.spring.jpa_security.repository.household_movement_address;

import com.nhnacademy.spring.jpa_security.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.spring.jpa_security.domain.household_movement_address.QHouseholdMovementAddressDto;
import com.nhnacademy.spring.jpa_security.entity.HouseholdMovementAddress;
import com.nhnacademy.spring.jpa_security.entity.QHouseholdMovementAddress;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class HouseholdMovementAddressRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMovementAddressRepositoryCustom {
    public HouseholdMovementAddressRepositoryImpl() {
        super(HouseholdMovementAddress.class);
    }


    @Override
    public List<HouseholdMovementAddressDto> getByHouseholdSerialNumber(Long householdSerialNumber) {
        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;

        return from(householdMovementAddress)
                .select(new QHouseholdMovementAddressDto(
                        householdMovementAddress.pk.houseMovementReportDate,
                        householdMovementAddress.houseMovementAddress,
                        householdMovementAddress.lastAddressYn.when("Y").then("현주소").otherwise("구주소")
                ))
                .fetch();
    }
}
