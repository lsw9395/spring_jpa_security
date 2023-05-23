package com.nhnacademy.spring.jpa_security.service.certificate_issue;

import com.nhnacademy.spring.jpa_security.domain.CertificateCode;
import com.nhnacademy.spring.jpa_security.domain.QualificationCode;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateBirthReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateDeathReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateFamilyRelationshipDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateResidentRegistrationDto;
import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentDto;
import com.nhnacademy.spring.jpa_security.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentFamilyDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRelationDto;
import com.nhnacademy.spring.jpa_security.entity.CertificateIssue;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.exception.NotFoundException;
import com.nhnacademy.spring.jpa_security.repository.birth_death_report_resident.BirthDeathResidentRepository;
import com.nhnacademy.spring.jpa_security.repository.certificate_issue.CertificateIssueRepository;
import com.nhnacademy.spring.jpa_security.repository.household.HouseholdRepository;
import com.nhnacademy.spring.jpa_security.repository.household_composition_resident.HouseholdCompositionResidentRepository;
import com.nhnacademy.spring.jpa_security.repository.household_movement_address.HouseholdMovementAddressRepository;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class CertificateIssueServiceImpl implements CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    private final BirthDeathResidentRepository birthDeathResidentRepository;

    public CertificateIssueServiceImpl(CertificateIssueRepository certificateIssueRepository,
                                       ResidentRepository residentRepository,
                                       HouseholdRepository householdRepository,
                                       HouseholdMovementAddressRepository householdMovementAddressRepository,
                                       HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                                       BirthDeathResidentRepository birthDeathResidentRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
        this.residentRepository = residentRepository;
        this.householdRepository = householdRepository;
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.birthDeathResidentRepository = birthDeathResidentRepository;
    }

    @Override
    public CertificateFamilyRelationshipDto getFamilyRelationshipCertificate(Long serialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, serialNumber));

        registerCertificate(resident, CertificateCode.RELATION);

        CertificateFamilyRelationshipDto certificateFamilyRelationship = certificateIssueRepository.getFamilyRelationCertificateByResident(resident);

        List<ResidentRelationDto> family =
                residentRepository.getAllResidentRelationByResidentSerialNumber(serialNumber);

        certificateFamilyRelationship.setFamily(family);

        return certificateFamilyRelationship;
    }

    @Override
    public CertificateResidentRegistrationDto getResidentRegistrationCertificate(Long serialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, serialNumber));

        registerCertificate(resident, CertificateCode.REGISTRATION);

        CertificateResidentRegistrationDto certificateResidentRegistration = new CertificateResidentRegistrationDto();
        if (householdRepository.existsByHouseholdResident(resident)) {
            certificateResidentRegistration = certificateIssueRepository.getResidentRegistrationCertificateByResident(resident);

            Long householdSerialNumber = certificateResidentRegistration.getHouseholdSerialNumber();
            List<HouseholdMovementAddressDto> address = householdMovementAddressRepository.getByHouseholdSerialNumber(householdSerialNumber);
            List<HouseholdCompositionResidentDto> composition = householdCompositionResidentRepository.getByHouseholdSerialNumber(householdSerialNumber);

            certificateResidentRegistration.setAddress(address);
            certificateResidentRegistration.setComposition(composition);
        }
        certificateResidentRegistration.setResident(resident);

        return certificateResidentRegistration;
    }

    @Override
    public CertificateBirthReportDto getBirthReportCertificate(Long serialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, serialNumber));

        registerCertificate(resident, CertificateCode.BIRTH);

        CertificateBirthReportDto birthReportCertificate =
                birthDeathResidentRepository.getBirthReportCertificateByResident(resident);

        ResidentFamilyDto father = getFamily(resident, QualificationCode.FATHER);
        ResidentFamilyDto mother = getFamily(resident, QualificationCode.MOTHER);

        birthReportCertificate.setFather(father);
        birthReportCertificate.setMother(mother);

        return birthReportCertificate;
    }

    @Override
    public CertificateDeathReportDto getDeathReportCertificate(Long serialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, serialNumber));

        registerCertificate(resident, CertificateCode.DEATH);

        CertificateDeathReportDto deathReportCertificate =
                birthDeathResidentRepository.getDeathReportCertificateByResident(resident);

        return deathReportCertificate;
    }

    @Override
    public Page<CertificateIssue> getCertificateIssues(Pageable pageable, Long serialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, serialNumber));

        return certificateIssueRepository.getAllByResident(pageable, resident);
    }

    private ResidentFamilyDto getFamily(Resident resident, QualificationCode code) {
        ResidentFamilyDto family = residentRepository.getByResident(resident, code);
        if (Objects.isNull(family)) {
            throw new NotFoundException(Resident.class, code.toString());
        }
        return family;
    }

    public void registerCertificate(Resident resident, CertificateCode code) {
        CertificateIssue certificateIssue = new CertificateIssue();

        Long certificateConfirmationNumber = getValidCertificateConfirmationNumber();

        certificateIssue.setCertificateConfirmationNumber(certificateConfirmationNumber);
        certificateIssue.setResident(resident);
        certificateIssue.setCertificateTypeCode(code.getValue());
        certificateIssue.setCertificateIssueDate(LocalDate.now());

        certificateIssueRepository.save(certificateIssue);
    }

    private Long getValidCertificateConfirmationNumber() {
        Long certificateConfirmationNumber;

        do {
            certificateConfirmationNumber = Long.parseLong(RandomStringUtils.random(16, "123456789"));
        } while (certificateIssueRepository.existsByCertificateConfirmationNumber(certificateConfirmationNumber));

        return certificateConfirmationNumber;
    }
}
