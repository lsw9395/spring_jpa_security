package com.nhnacademy.spring.jpa_security.repository.birth_death_report_resident;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateBirthReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateDeathReportDto;
import com.nhnacademy.spring.jpa_security.entity.BirthDeathReportResident;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.repository.resident.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class BirthDeathResidentRepositoryTest {
    @Autowired
    BirthDeathResidentRepository birthDeathResidentRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Test
    void existsByPk() {
    }

    @Test
    void updateByPk() {
    }

    @Test
    void getBirthReportCertificateByResident() {
        Resident resident = residentRepository.findByResidentSerialNumber(7L).get();
        System.out.println(resident.getName());
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(7L, "출생");
        BirthDeathReportResident reportResident = birthDeathResidentRepository.findByPk(pk).get();
        System.out.println(Arrays.stream(reportResident.getClass().getDeclaredFields()).map(x -> {
            try {
                x.setAccessible(true);
                return x.getName() + " : " + x.get(reportResident);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(", ")));
        CertificateBirthReportDto birth = birthDeathResidentRepository.getBirthReportCertificateByResident(resident);

        System.out.println(birth);
    }

    @Test
    void getDeathReportCertificateByResident() {
        Resident resident = residentRepository.findByResidentSerialNumber(1L).get();

        CertificateDeathReportDto birth = birthDeathResidentRepository.getDeathReportCertificateByResident(resident);

        System.out.println(birth);
    }
}