package com.nhnacademy.spring.jpa_security.repository.resident;

import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Long> {
    Optional<Resident> findById(String id);

    Optional<Resident> findByResidentSerialNumber(Long residentSerialNumber);

    Optional<Resident> findByEmail(String emailAddress);

    List<Resident> findBy(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Resident " +
            "set deathDate = :date, deathPlaceCode = :code, deathPlaceAddress = :address " +
            "where residentSerialNumber = :serialNumber")
    int updateBySerialNumber(@Param("serialNumber") Long residentSerialNumber,
                             @Param("date") LocalDateTime deathDate,
                             @Param("code") String deathPlaceCode,
                             @Param("address") String deathPlaceAddress);

    @Transactional
    void deleteByResidentSerialNumber(Long residentSerialNumber);
}
