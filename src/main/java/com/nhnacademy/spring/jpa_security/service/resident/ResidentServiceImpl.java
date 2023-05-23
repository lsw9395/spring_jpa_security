package com.nhnacademy.spring.jpa_security.service.resident;

import com.nhnacademy.spring.jpa_security.domain.resident.ResidentListDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRegisterRequest;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentViewDto;
import com.nhnacademy.spring.jpa_security.entity.Household;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.exception.AlreadyRegisteredException;
import com.nhnacademy.spring.jpa_security.exception.NotFoundException;
import com.nhnacademy.spring.jpa_security.exception.ResidentDeleteFailedException;
import com.nhnacademy.spring.jpa_security.repository.birth_death_report_resident.BirthDeathResidentRepository;
import com.nhnacademy.spring.jpa_security.repository.certificate_issue.CertificateIssueRepository;
import com.nhnacademy.spring.jpa_security.repository.family_relationship.FamilyRelationshipRepository;
import com.nhnacademy.spring.jpa_security.repository.household.HouseholdRepository;
import com.nhnacademy.spring.jpa_security.repository.household_composition_resident.HouseholdCompositionResidentRepository;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.assertj.core.util.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ResidentServiceImpl implements ResidentService {
    private final PasswordEncoder passwordEncoder;
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final BirthDeathResidentRepository birthDeathResidentRepository;
    private final CertificateIssueRepository certificateIssueRepository;

    public ResidentServiceImpl(PasswordEncoder passwordEncoder,
                               ResidentRepository residentRepository,
                               HouseholdRepository householdRepository,
                               FamilyRelationshipRepository familyRelationshipRepository,
                               HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                               BirthDeathResidentRepository birthDeathResidentRepository,
                               CertificateIssueRepository certificateIssueRepository) {
        this.passwordEncoder = passwordEncoder;
        this.residentRepository = residentRepository;
        this.householdRepository = householdRepository;
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.birthDeathResidentRepository = birthDeathResidentRepository;
        this.certificateIssueRepository = certificateIssueRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Resident getResident(Long residentSerialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(residentSerialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, residentSerialNumber));

        return resident;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resident> getResidents(Pageable pageable) {
        return residentRepository.findBy(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ResidentViewDto getResidentForView(Long residentSerialNumber) {
        ResidentViewDto resident = residentRepository.getResidentViewByResidentSerialNumber(residentSerialNumber);

        if(Objects.isNull(resident)) {
            throw new NotFoundException(Resident.class, residentSerialNumber);
        }
        return resident;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ResidentListDto> getResidentsForResidentList(Pageable pageable, String userId) {
        Resident resident = residentRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Resident.class, userId));

        if(householdCompositionResidentRepository.existsByResident(resident)) {
            return residentRepository.getAllBy(pageable, userId);
        }
        ResidentListDto residentListDto = new ResidentListDto();
        residentListDto.setResidentSerialNumber(resident.getResidentSerialNumber());
        residentListDto.setName(resident.getName());
        residentListDto.setGenderCode(resident.getGenderCode());
        residentListDto.setResidentRegistrationNumber(resident.getResidentRegistrationNumber());
        residentListDto.setRegistrationBaseAddress(resident.getRegistrationBaseAddress());

        return new PageImpl<>(Lists.list(residentListDto), pageable, 1);
    }

    @Override
    public Resident registerResident(ResidentRegisterRequest request) {
        if (residentRepository.existsById(request.getResidentSerialNumber())) {
            throw new AlreadyRegisteredException(Resident.class, request.getResidentSerialNumber());
        }
        Resident resident = new Resident();

        resident.setResidentSerialNumber(request.getResidentSerialNumber());
        resident.setName(request.getName());
        resident.setId(request.getId());
        resident.setPassword(passwordEncoder.encode(request.getPassword()));
        resident.setEmail(request.getEmail());
        resident.setResidentRegistrationNumber(request.getResidentRegistrationNumber());
        resident.setGenderCode(request.getGenderCode());
        resident.setBirthDate(request.getBirthDate());
        resident.setBirthPlaceCode(request.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(request.getRegistrationBaseAddress());

        return residentRepository.save(resident);
    }

    @Override
    public int updateResident(Long serialNumber, ResidentModifyRequest request) {
        if (!residentRepository.existsById(serialNumber)) {
            throw new NotFoundException(Resident.class, serialNumber);
        }

        return residentRepository.updateBySerialNumber(serialNumber,
                request.getDeathDate(),
                request.getDeathPlaceCode(),
                request.getDeathPlaceAddress());
    }

    @Override
    public void deleteResident(Long residentSerialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(residentSerialNumber)
                .orElseThrow(() -> new NotFoundException(Resident.class, residentSerialNumber));

        Household household;
        if((household = householdRepository.findByHouseholdResident(resident)
                .orElse(null)) != null) {
            if(householdCompositionResidentRepository.countByPk_HouseholdSerialNumber(household.getHouseholdSerialNumber()) > 1) {
                throw new ResidentDeleteFailedException();
            }
            else {
                householdRepository.deleteByHouseholdSerialNumber(household.getHouseholdSerialNumber());
            }
        }
        familyRelationshipRepository.deleteByResidentSerialNumber(residentSerialNumber);
        certificateIssueRepository.deleteByResidentSerialNumber(residentSerialNumber);
        birthDeathResidentRepository.deleteByResidentSerialNumber(residentSerialNumber);
        residentRepository.deleteByResidentSerialNumber(residentSerialNumber);
    }
}
