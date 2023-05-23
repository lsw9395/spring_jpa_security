package com.nhnacademy.spring.jpa_security.repository.resident;

import com.nhnacademy.spring.jpa_security.domain.QualificationCode;
import com.nhnacademy.spring.jpa_security.domain.resident.*;
import com.nhnacademy.spring.jpa_security.entity.*;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(Resident.class);
    }


    @Override
    public ResidentFamilyDto getByResident(Resident residentO, QualificationCode code) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;

        return from(familyRelationship)
                .innerJoin(familyRelationship.familyResident, resident)
                .select(new QResidentFamilyDto(
                        resident.name,
                        resident.residentRegistrationNumber
                ))
                .where(familyRelationship.resident.eq(residentO))
                .where(familyRelationship.familyRelationshipCode.eq(code.toString()))
                .fetchOne();
    }

    @Override
    public Page<ResidentListDto> getAllBy(Pageable pageable, String userId) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QHousehold household = QHousehold.household;
        QResident resident = QResident.resident;

        Household targetHousehold = from(householdCompositionResident)
                .select(householdCompositionResident.household)
                .where(householdCompositionResident.resident.id.eq(userId))
                .fetchOne();

        List<ResidentListDto> content =
                from(householdCompositionResident)
                        .innerJoin(householdCompositionResident.household, household)
                        .innerJoin(householdCompositionResident.resident, resident)
                        .select(new QResidentListDto(
                                resident.residentSerialNumber,
                                resident.name,
                                resident.genderCode,
                                resident.residentRegistrationNumber,
                                resident.registrationBaseAddress
                        ))
                        .where(household.eq(targetHousehold))
                        .limit(pageable.getPageSize())
                        .offset(pageable.getOffset())
                        .distinct()
                        .fetch();

        long total = from(householdCompositionResident)
                .select(householdCompositionResident.count())
                .where(householdCompositionResident.household.eq(targetHousehold))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public ResidentViewDto getResidentViewByResidentSerialNumber(Long residentSerialNumber) {
        QResident resident = QResident.resident;
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;

        NumberExpression<Integer> birthReport = birthDeathReportResident.pk.birthDeathTypeCode
                .when("출생")
                .then(new Integer(1))
                .otherwise(new Integer(0));
        NumberExpression<Integer> deathReport = birthDeathReportResident.pk.birthDeathTypeCode
                .when("사망")
                .then(new Integer(1))
                .otherwise(new Integer(0));

        return from(birthDeathReportResident)
                .rightJoin(birthDeathReportResident.resident, resident)
                .select(new QResidentViewDto(
                        resident.name,
                        resident.residentRegistrationNumber,
                        resident.genderCode,
                        birthReport.sum(),
                        deathReport.sum())
                )
                .where(resident.residentSerialNumber.eq(residentSerialNumber))
                .groupBy(resident.residentSerialNumber)
                .fetchOne();
    }

    @Override
    public List<ResidentRelationDto> getAllResidentRelationByResidentSerialNumber(Long residentSerialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;
        QResident familyResident = QResident.resident;

        return from(familyRelationship)
                .rightJoin(familyRelationship.resident, resident)
                .innerJoin(familyRelationship.familyResident, familyResident)
                .select(new QResidentRelationDto(
                        familyRelationship.familyRelationshipCode,
                        familyResident.name,
                        familyResident.birthDate,
                        familyResident.residentRegistrationNumber,
                        familyResident.genderCode))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNumber))
                .fetch();
    }
}
