package com.dvp.technical_test.application.dtos.ticket;

import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

import com.dvp.technical_test.domain.ticket.entity.Status;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDTO {
    private String id;
    @NotBlank(message = "description is required")
    @Length(max = 500)
    private String description;
    @NotBlank(message = "personId is required")
    private String personId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Status status;
}
