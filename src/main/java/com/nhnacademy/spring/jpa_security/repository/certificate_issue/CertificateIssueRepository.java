package com.nhnacademy.spring.jpa_security.repository.certificate_issue;

import com.nhnacademy.spring.jpa_security.entity.CertificateIssue;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CertificateIssueRepository extends CertificateIssueRepositoryCustom, JpaRepository<CertificateIssue, Long> {
    boolean existsByResidentAndCertificateTypeCode(Resident resident, String certificateTypeCode);

    boolean existsByCertificateConfirmationNumber(Long certificateConfirmationNumber);

    Page<CertificateIssue> getAllByResident(Pageable pageable, Resident resident);

    @Transactional
    @Modifying
    @Query("delete from CertificateIssue " +
            "where resident.residentSerialNumber = :serialNumber")
    void deleteByResidentSerialNumber(@Param("serialNumber") Long residentSerialNumber);
}

