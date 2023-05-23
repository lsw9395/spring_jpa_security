package com.nhnacademy.spring.jpa_security.controller;

import com.nhnacademy.spring.jpa_security.domain.household.HouseholdRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.Household;
import com.nhnacademy.spring.jpa_security.service.household.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
public class HouseholdRestController {
    private  final HouseholdService householdService;


    @PostMapping
    public ResponseEntity<Household> registerHousehold(@RequestBody HouseholdRegisterRequest request) {
        Household household = householdService.registerHousehold(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(household);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity deleteHousehold(@PathVariable("householdSerialNumber") Long householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);

        return ResponseEntity.ok().build();
    }
}
