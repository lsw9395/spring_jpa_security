package com.nhnacademy.spring.jpa_security.repository.certificate_issue;

import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateFamilyRelationshipDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateResidentRegistrationDto;
import com.nhnacademy.spring.jpa_security.entity.Resident;

public interface CertificateIssueRepositoryCustom {
    CertificateFamilyRelationshipDto getFamilyRelationCertificateByResident(Resident resident);

    CertificateResidentRegistrationDto getResidentRegistrationCertificateByResident(Resident resident);
}
