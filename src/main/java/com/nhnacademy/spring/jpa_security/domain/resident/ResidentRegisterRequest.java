package com.nhnacademy.spring.jpa_security.domain.resident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentRegisterRequest {
    @NotBlank
    private Long residentSerialNumber;
    @NotBlank
    @Length(max = 100)
    private String name;
    private String id;
    private String password;
    private String email;
    @NotBlank
    @Length(max = 300)
    private String residentRegistrationNumber;
    @NotBlank
    @Length(max = 20)
    private String genderCode;
    @NotBlank
    private LocalDateTime birthDate;
    @NotBlank
    @Length(max = 20)
    private String birthPlaceCode;
    @NotBlank
    @Length(max = 500)
    private String registrationBaseAddress;
}
