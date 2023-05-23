package com.nhnacademy.spring.jpa_security.service.resident;

import com.nhnacademy.spring.jpa_security.domain.resident.ResidentListDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRegisterRequest;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentViewDto;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResidentService {

    Resident getResident(Long residentSerialNumber);

    List<Resident> getResidents(Pageable pageable);

    ResidentViewDto getResidentForView(Long residentSerialNumber);

    Page<ResidentListDto> getResidentsForResidentList(Pageable pageable, String userId);

    Resident registerResident(ResidentRegisterRequest request);

    int updateResident(Long serialNumber, ResidentModifyRequest request);


    void deleteResident(Long residentSerialNumber);
}
