package com.nhnacademy.spring.jpa_security.domain.household_movement_address;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HouseholdMovementAddressDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate houseMovementReportDate;
    String houseMovementAddress;
    String lastAddressYn;

    @QueryProjection
    public HouseholdMovementAddressDto(LocalDate houseMovementReportDate, String houseMovementAddress, String lastAddressYn) {
        this.houseMovementReportDate = houseMovementReportDate;
        this.houseMovementAddress = houseMovementAddress;
        this.lastAddressYn = lastAddressYn;
    }
}
