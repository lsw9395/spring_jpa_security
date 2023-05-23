package com.nhnacademy.spring.jpa_security.repository.resident;

import com.nhnacademy.spring.jpa_security.domain.QualificationCode;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentFamilyDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentListDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRelationDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentViewDto;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    ResidentFamilyDto getByResident(Resident residentO, QualificationCode code);

    Page<ResidentListDto> getAllBy(Pageable pageable, String userId);


    ResidentViewDto getResidentViewByResidentSerialNumber(Long residentSerialNumber);

    List<ResidentRelationDto> getAllResidentRelationByResidentSerialNumber(Long residentSerialNumber);
}
