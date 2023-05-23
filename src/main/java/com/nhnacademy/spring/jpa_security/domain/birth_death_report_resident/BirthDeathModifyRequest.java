package com.nhnacademy.spring.jpa_security.domain.birth_death_report_resident;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathModifyRequest {
    String emailAddress;
    String phoneNumber;
}
