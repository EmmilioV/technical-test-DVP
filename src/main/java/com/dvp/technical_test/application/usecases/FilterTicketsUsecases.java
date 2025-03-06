package com.dvp.technical_test.application.usecases;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dvp.technical_test.application.dtos.ticket.TicketConverter;
import com.dvp.technical_test.application.dtos.ticket.TicketDTO;
import com.dvp.technical_test.domain.ticket.entity.Status;
import com.dvp.technical_test.domain.ticket.repository.TicketRepository;

@Service
public class FilterTicketsUsecases {
    private final TicketRepository ticketRepository;
    private final TicketConverter ticketConverter;

    public FilterTicketsUsecases(
            TicketRepository ticketRepository,
            TicketConverter ticketConverter) {
        this.ticketRepository = ticketRepository;
        this.ticketConverter = ticketConverter;
    }

    public List<TicketDTO> filterByStatus(Status status) {
        return ticketRepository.findByStatus(status)
                .stream()
                .map(ticketConverter::toTicketDTOFromTicket)
                .toList();
    }

    public List<TicketDTO> filterByPersonId(String personId) {
        return ticketRepository.findByPersonId(UUID.fromString(personId))
                .stream()
                .map(ticketConverter::toTicketDTOFromTicket)
                .toList();
    }

    public List<TicketDTO> filterByStatusAndPersonId(Status status, String personId) {
        return ticketRepository.findByStatusAndPersonId(status, UUID.fromString(personId))
                .stream()
                .map(ticketConverter::toTicketDTOFromTicket)
                .toList();
    }
}
