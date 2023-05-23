package com.nhnacademy.spring.jpa_security.controller;

import com.nhnacademy.spring.jpa_security.domain.resident.ResidentModifyRequest;
import com.nhnacademy.spring.jpa_security.domain.resident.ResidentRegisterRequest;
import com.nhnacademy.spring.jpa_security.entity.Resident;
import com.nhnacademy.spring.jpa_security.exception.ValidationFailedException;
import com.nhnacademy.spring.jpa_security.service.resident.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService residentService;
    @GetMapping
    public List<Resident> queryResidents(Pageable pageable) {
        return residentService.getResidents(pageable);
    }

    @GetMapping("/{serialNumber}")
    public Resident queryResident(@PathVariable("serialNumber") Long serialNumber) {
        return residentService.getResident(serialNumber);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Resident registerResident(@RequestBody ResidentRegisterRequest request,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return residentService.registerResident(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{serialNumber}")
    public int updateResident(@PathVariable("serialNumber") Long serialNumber,
                                   @RequestBody ResidentModifyRequest request,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return residentService.updateResident(serialNumber, request);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{serialNumber}")
    public void deleteResident(@PathVariable("serialNumber") Long serialNumber) {
        residentService.deleteResident(serialNumber);
    }
}
