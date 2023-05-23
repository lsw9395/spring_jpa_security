package com.nhnacademy.spring.jpa_security.service.birth_death_report_resident;

import com.nhnacademy.spring.jpa_security.domain.BirthDeathCode;

import com.nhnacademy.spring.jpa_security.domain.birth_death_report_resident.BirthDeathModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.birth_death_report_resident.BirthDeathRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    BirthDeathReportResident registerBirthReportResident(Long serialNumber, BirthDeathRegisterRequest request);

    BirthDeathReportResident registerDeathReportResident(Long serialNumber, BirthDeathRegisterRequest request);

    void updateBirthDeathReportResident(Long serialNumber, Long targetSerialNumber, BirthDeathModifyRequest request, BirthDeathCode code);

    void deleteBirthDeathReportResident(Long serialNumber, Long targetSerialNumber, BirthDeathCode code);
}
