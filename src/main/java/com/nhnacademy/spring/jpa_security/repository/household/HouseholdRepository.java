package com.nhnacademy.spring.jpa_security.repository.household;

import com.nhnacademy.spring.jpa_security.entity.Household;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
    boolean existsByHouseholdSerialNumber(Long householdSerialNumber);

    boolean existsByHouseholdResident(Resident householdResident);

    Optional<Household> findByHouseholdResident(Resident householdResident);

    Optional<Household> findByHouseholdSerialNumber(Long householdSerialNumber);

    @Transactional
    void deleteByHouseholdSerialNumber(Long householdSerialNumber);
}
