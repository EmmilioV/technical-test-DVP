package com.dvp.technical_test.application.dtos.ticket;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dvp.technical_test.domain.ticket.entity.Ticket;

@Service
public class TicketConverter {
    public Ticket toTicketFromTicketDTO(TicketDTO ticketDTO) {
        return Ticket.builder()
                .id(ticketDTO.getId() == null || ticketDTO.getId().isBlank()
                        ? null
                        : UUID.fromString(ticketDTO.getId()))
                .description(ticketDTO.getDescription())
                .personId(UUID.fromString(ticketDTO.getPersonId()))
                .createdDate(ticketDTO.getCreatedDate())
                .updatedDate(ticketDTO.getUpdatedDate())
                .status(ticketDTO.getStatus())
                .build();
    }

    public TicketDTO toTicketDTOFromTicket(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId().toString())
                .description(ticket.getDescription())
                .personId(ticket.getPersonId().toString())
                .createdDate(ticket.getCreatedDate())
                .updatedDate(ticket.getUpdatedDate())
                .status(ticket.getStatus())
                .build();
    }
}
