package com.nhnacademy.spring.jpa_security.repository.certificate_issue;

import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateFamilyRelationshipDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateResidentRegistrationDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.QCertificateFamilyRelationshipDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.QCertificateResidentRegistrationDto;
import com.nhnacademy.spring.jpa_security.entity.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CertificateIssueRepositoryImpl extends QuerydslRepositorySupport implements CertificateIssueRepositoryCustom {
    public CertificateIssueRepositoryImpl() {
        super(CertificateIssue.class);
    }

    @Override
    public CertificateFamilyRelationshipDto getFamilyRelationCertificateByResident(Resident residentO) {
        QResident resident = QResident.resident;
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;

        return from(certificateIssue)
                .innerJoin(certificateIssue.resident, resident)
                .select(new QCertificateFamilyRelationshipDto(
                        certificateIssue.certificateIssueDate,
                        certificateIssue.certificateConfirmationNumber,
                        resident.registrationBaseAddress,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode))
                .where(certificateIssue.resident.eq(residentO))
                .where(certificateIssue.certificateTypeCode.eq("가족관계증명서"))
                .orderBy(certificateIssue.certificateIssueDate.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public CertificateResidentRegistrationDto getResidentRegistrationCertificateByResident(Resident residentO) {
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;
        QHousehold household = QHousehold.household;
        QResident resident = QResident.resident;

        return from(certificateIssue)
                .innerJoin(certificateIssue.resident, resident)
                .innerJoin(household)
                .on(certificateIssue.resident.eq(household.householdResident))
                .select(new QCertificateResidentRegistrationDto(
                        certificateIssue.certificateIssueDate,
                        certificateIssue.certificateConfirmationNumber,
                        household.householdSerialNumber,
                        resident.name,
                        household.householdCompositionReasonCode,
                        household.householdCompositionDate
                ))
                .where(certificateIssue.resident.eq(residentO))
                .where(certificateIssue.certificateTypeCode.eq("주민등록등본"))
                .orderBy(certificateIssue.certificateIssueDate.desc())
                .limit(1)
                .fetchOne();
    }
}
