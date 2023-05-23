package com.nhnacademy.spring.jpa_security.repository.birth_death_report_resident;

import com.nhnacademy.spring.jpa_security.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface BirthDeathResidentRepository extends BirthDeathResidentRepositoryCustom, JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    boolean existsByPk(BirthDeathReportResident.Pk pk);

    Optional<BirthDeathReportResident> findByPk(BirthDeathReportResident.Pk pk);

    @Modifying
    @Transactional
    @Query("update BirthDeathReportResident " +
            "set emailAddress = :email, " +
            "phoneNumber = :phoneNumber " +
            "where pk = :pk")
    void updateByPk(@Param("pk") BirthDeathReportResident.Pk pk,
                    @Param("email") String emilAddress,
                    @Param("phoneNumber") String phoneNumber);

    @Transactional
    void deleteByPk(BirthDeathReportResident.Pk pk);

    @Transactional
    @Modifying
    @Query("delete from BirthDeathReportResident " +
            "where pk.residentSerialNumber = :serialNumber")
    void deleteByResidentSerialNumber(@Param("serialNumber") Long residentSerialNumber);
}
