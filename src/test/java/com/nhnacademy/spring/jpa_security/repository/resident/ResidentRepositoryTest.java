package com.nhnacademy.spring.jpa_security.repository.resident;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.domain.QualificationCode;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentFamilyDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentListDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRelationDto;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentViewDto;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentRepositoryTest {
    @Autowired
    private ResidentRepository residentRepository;

    @Test
    void findByResidentSerialNumber() {
        Long residentSerialNumber = 3L;

        Optional<Resident> result = residentRepository.findByResidentSerialNumber(residentSerialNumber);

        assertThat(result).isPresent();
        assertThat(result.get().getResidentSerialNumber()).isEqualTo(residentSerialNumber);
    }

    @Test
    void findBy() {
        int pageSize = 5;
        Pageable pageable = Pageable.ofSize(pageSize);

        List<Resident> result = residentRepository.findBy(pageable);

        assertThat(result).isNotNull();
        assertThat(result.size()).isLessThanOrEqualTo(pageSize);
    }

    @Test
    void findById() {
        Resident resident = residentRepository.findById("user1").orElse(null);
        System.out.println(resident);
        String a = Arrays.stream(resident.getClass().getDeclaredFields()).map(s -> {
            try {
                s.setAccessible(true);
                return s.getName() + " : " + s.get(resident);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(" , "));
        System.out.println(a);

    }
    @Test
    void getAllBy() {
        Pageable pageable = Pageable.ofSize(10);

        Page<ResidentListDto> result = residentRepository.getAllBy(pageable, "yerin");

        result.stream().forEach(System.out::println);
    }

    @Test
    void getResidentViewByResidentSerialNumber() {
        Long serialNumber = 1L;
        ResidentViewDto result = residentRepository.getResidentViewByResidentSerialNumber(serialNumber);

        System.out.println(result);
    }

    @Test
    void updateBySerialNumber() {
        Long serialNumber = 10L;
        LocalDateTime deathDate = LocalDateTime.now();
        String deathPlaceCode = "집";
        String deathPlaceAddress = "방";

        residentRepository.updateBySerialNumber(serialNumber, deathDate, deathPlaceCode, deathPlaceAddress);

        Optional<Resident> resident = residentRepository.findByResidentSerialNumber(serialNumber);

        assertThat(resident).isPresent();
        assertThat(resident.get().getDeathPlaceCode()).isEqualTo(deathPlaceCode);
        assertThat(resident.get().getDeathPlaceAddress()).isEqualTo(deathPlaceAddress);
    }

    @Test
    void deleteByResidentSerialNumber() {
        Long serialNumber = 10L;

        residentRepository.deleteByResidentSerialNumber(serialNumber);

        assertThat(residentRepository.findByResidentSerialNumber(serialNumber)).isEmpty();
    }
    @Test
    void getResidentRelationByResidentSerialNumber() {
        List<ResidentRelationDto> residents = residentRepository.getAllResidentRelationByResidentSerialNumber(2L);
        residents.stream().forEach(System.out::println);
    }

    @Test
    void getByResident() {
        Resident resident = residentRepository.findByResidentSerialNumber(7L).get();
        ResidentFamilyDto father = residentRepository.getByResident(resident, QualificationCode.FATHER);

        System.out.println(father);
    }
}