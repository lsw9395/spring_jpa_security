package com.nhnacademy.spring.jpa_security.repository.household_composition_resident;

import com.nhnacademy.spring.jpa_security.entity.HouseholdCompositionResident;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HouseholdCompositionResidentRepository extends HouseholdCompositionResidentRepositoryCustom,
        JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {
    boolean existsByPk(HouseholdCompositionResident.Pk pk);

    boolean existsByResident(Resident resident);

    int countByPk_HouseholdSerialNumber(Long householdSerialNumber);

    @Transactional
    void deleteByPk(HouseholdCompositionResident.Pk pk);
}
