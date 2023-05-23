package com.nhnacademy.spring.jpa_security.repository.household_movement_address;

import com.nhnacademy.spring.jpa_security.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HouseholdMovementAddressRepository extends HouseholdMovementAddressRepositoryCustom, JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
    boolean existsByPk(HouseholdMovementAddress.Pk pk);

    @Modifying
    @Transactional
    @Query("update HouseholdMovementAddress " +
            "set lastAddressYn = :lastAddressYn " +
            "where pk = :pk")
    void updateLastAddressYnByPk(@Param("pk") HouseholdMovementAddress.Pk pk,
                                 @Param("lastAddressYn") String lastAddressYn);

    @Transactional
    void deleteByPk(HouseholdMovementAddress.Pk pk);

    @Transactional
    void deleteByPk_HouseholdSerialNumber(Long householdSerialNumber);
}
