package com.nhnacademy.spring.jpa_security.repository.certificate_issue;

import com.nhnacademy.spring.jpa_security.config.RootConfig;
import com.nhnacademy.spring.jpa_security.config.WebConfig;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateResidentRegistrationDto;
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

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CertificateIssueRepositoryTest {
    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    CertificateIssueRepository certificateIssueRepository;

    @Test
    void getResidentRegistrationCertificateByResident() {
        Resident resident = residentRepository.findByResidentSerialNumber(4L).get();

        CertificateResidentRegistrationDto residents = certificateIssueRepository.getResidentRegistrationCertificateByResident(resident);

        System.out.println(resident);
    }
}