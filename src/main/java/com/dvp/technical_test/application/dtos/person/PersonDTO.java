package com.dvp.technical_test.application.dtos.person;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private String id;
    @NotBlank(message = "first name is required")
    private String firstName;
    @NotBlank(message = "last name is required")
    private String lastName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
