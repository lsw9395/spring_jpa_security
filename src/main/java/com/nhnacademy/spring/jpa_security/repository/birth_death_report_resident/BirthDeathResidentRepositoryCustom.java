package com.nhnacademy.spring.jpa_security.repository.birth_death_report_resident;

import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateBirthReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateDeathReportDto;
import com.nhnacademy.spring.jpa_security.entity.Resident;

public interface BirthDeathResidentRepositoryCustom {

    CertificateBirthReportDto getBirthReportCertificateByResident(Resident residentO);

    CertificateDeathReportDto getDeathReportCertificateByResident(Resident resident);
}
