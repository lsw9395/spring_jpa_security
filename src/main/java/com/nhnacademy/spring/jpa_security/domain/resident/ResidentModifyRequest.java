package com.nhnacademy.spring.jpa_security.domain.resident;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentModifyRequest {
    @NotBlank
    private LocalDateTime deathDate;
    @NotBlank
    @Length(max = 20)
    private String deathPlaceCode;
    @NotBlank
    @Length(max = 500)
    private String deathPlaceAddress;
}
