package com.nhnacademy.spring.jpa_security.controller;

import com.nhnacademy.spring.jpa_security.domain.household_composition_resident.HouseholdCompositionResidentRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.HouseholdCompositionResident;
import com.nhnacademy.spring.jpa_security.service.household_composition_resident.HouseholdCompositionResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household/{householdSerialNumber}/composition")
@RequiredArgsConstructor
public class HouseholdCompositionResidentRestController {
    private final HouseholdCompositionResidentService householdCompositionResidentService;


    @PostMapping
    public ResponseEntity<HouseholdCompositionResident>
    registerHouseholdCompositionResident(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                         @RequestBody HouseholdCompositionResidentRegisterRequest request) {
        HouseholdCompositionResident householdCompositionResident =
                householdCompositionResidentService.registerHouseholdCompositionResident(householdSerialNumber, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(householdCompositionResident);
    }

    @DeleteMapping("/{residentSerialNumber}")
    public ResponseEntity deleteHouseholdCompositionResident(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                                             @PathVariable("residentSerialNumber") Long residentSerialNumber) {
        householdCompositionResidentService.deleteHouseholdCompositionResident(householdSerialNumber, residentSerialNumber);

        return ResponseEntity.ok().build();
    }
}

