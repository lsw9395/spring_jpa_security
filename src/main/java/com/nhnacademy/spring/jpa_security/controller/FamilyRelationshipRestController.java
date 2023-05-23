package com.nhnacademy.spring.jpa_security.controller;

import com.nhnacademy.spring.jpa_security.domain.family_relationship.FamilyRelationshipModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.family_relationship.FamilyRelationshipRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.FamilyRelationship;
import com.nhnacademy.spring.jpa_security.service.family_relationship.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
@RequiredArgsConstructor
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;


    @PostMapping
    public ResponseEntity<FamilyRelationship> registerFamilyRelationship(@PathVariable("serialNumber") Long serialNumber,
                                                                         @RequestBody FamilyRelationshipRegisterRequest request) {
        FamilyRelationship familyRelationship =  familyRelationshipService.registerFamilyRelationship(serialNumber, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(familyRelationship);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{familySerialNumber}")
    public ResponseEntity updateFamilyRelationship(@PathVariable("serialNumber") Long residentSerialNumber,
                                          @PathVariable("familySerialNumber") Long familySerialNumber,
                                          @RequestBody FamilyRelationshipModifyRequest request) {
        familyRelationshipService.updateFamilyRelationship(residentSerialNumber, familySerialNumber, request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{familySerialNumber}")
    public ResponseEntity deleteFamilyRelationship(@PathVariable("serialNumber") Long residentSerialNumber,
                                         @PathVariable("familySerialNumber") Long familySerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(residentSerialNumber, familySerialNumber);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
