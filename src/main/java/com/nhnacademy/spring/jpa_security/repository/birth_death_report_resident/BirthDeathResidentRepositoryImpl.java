package com.nhnacademy.spring.jpa_security.repository.birth_death_report_resident;

import com.nhnacademy.spring.jpa_security.domain.BirthDeathCode;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateBirthReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateDeathReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.QCertificateBirthReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.QCertificateDeathReportDto;
import com.nhnacademy.spring.jpa_security.entity.BirthDeathReportResident;
import com.nhnacademy.spring.jpa_security.entity.QBirthDeathReportResident;
import com.nhnacademy.spring.jpa_security.entity.QResident;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BirthDeathResidentRepositoryImpl extends QuerydslRepositorySupport implements BirthDeathResidentRepositoryCustom{
    public BirthDeathResidentRepositoryImpl() {
        super(BirthDeathReportResident.class);
    }

    @Override
    public CertificateBirthReportDto getBirthReportCertificateByResident(Resident residentO) {
        QResident resident = QResident.resident;
        QResident reportResident = new QResident("reportResident");
        QBirthDeathReportResident birthReportResident = QBirthDeathReportResident.birthDeathReportResident;

        return from(birthReportResident)
                .innerJoin(birthReportResident.resident, resident)
                .innerJoin(birthReportResident.reportResident, reportResident)
                .select(new QCertificateBirthReportDto(
                        birthReportResident.birthDeathReportDate,
                        resident.name,
                        resident.genderCode,
                        resident.birthDate,
                        resident.birthPlaceCode,
                        resident.registrationBaseAddress,
                        reportResident.name,
                        reportResident.residentRegistrationNumber,
                        birthReportResident.birthReportQualificationsCode,
                        birthReportResident.emailAddress,
                        birthReportResident.phoneNumber
                ))
                .where(birthReportResident.resident.eq(residentO))
                .where(birthReportResident.pk.birthDeathTypeCode.eq(BirthDeathCode.BIRTH.getValue()))
                .fetchOne();
    }

    @Override
    public CertificateDeathReportDto getDeathReportCertificateByResident(Resident residentO) {
        QResident resident = QResident.resident;
        QResident reportResident = new QResident("reportResident");
        QBirthDeathReportResident deathReportResident = QBirthDeathReportResident.birthDeathReportResident;

        return from(deathReportResident)
                .innerJoin(deathReportResident.resident, resident)
                .innerJoin(deathReportResident.reportResident, reportResident)
                .select(new QCertificateDeathReportDto(
                        deathReportResident.birthDeathReportDate,
                        resident.name,
                        resident.residentRegistrationNumber,
                        resident.deathDate,
                        resident.deathPlaceCode,
                        resident.deathPlaceAddress,
                        reportResident.name,
                        reportResident.residentRegistrationNumber,
                        deathReportResident.deathReportQualificationsCode,
                        deathReportResident.emailAddress,
                        deathReportResident.phoneNumber
                ))
                .where(deathReportResident.resident.eq(residentO))
                .where(deathReportResident.pk.birthDeathTypeCode.eq(BirthDeathCode.DEATH.getValue()))
                .fetchOne();
    }
}
