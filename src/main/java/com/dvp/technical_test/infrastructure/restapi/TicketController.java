package com.dvp.technical_test.infrastructure.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvp.technical_test.application.dtos.ticket.TicketDTO;
import com.dvp.technical_test.application.usecases.FilterTicketsUsecases;
import com.dvp.technical_test.application.usecases.TicketUsecases;
import com.dvp.technical_test.domain.ticket.entity.Status;
import com.dvp.technical_test.infrastructure.exception.MissingFieldsException;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketUsecases ticketUsecases;
    private final FilterTicketsUsecases filterTicketsUsecases;

    public TicketController(
            TicketUsecases ticketUsecases,
            FilterTicketsUsecases filterTicketsUsecases) {
        this.ticketUsecases = ticketUsecases;
        this.filterTicketsUsecases = filterTicketsUsecases;
    }

    @PostMapping("/create-one")
    public TicketDTO createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        if (ticketDTO.getStatus() == null) {
            throw new MissingFieldsException("status is required");
        }

        return ticketUsecases.createTicket(ticketDTO);
    }

    @PutMapping("/update-one")
    public TicketDTO putMethodName(@Valid @RequestBody TicketDTO ticketDTO) {
        if (ticketDTO.getId() == null || ticketDTO.getId().isBlank()) {
            throw new MissingFieldsException("id is required for update");
        }

        if (ticketDTO.getStatus() == null) {
            throw new MissingFieldsException("status is required");
        }

        if (ticketDTO.getCreatedDate() == null) {
            throw new MissingFieldsException("created date is missing");
        }

        return ticketUsecases.update(ticketDTO);
    }

    @DeleteMapping("/delete-one/{id}")
    public void deleteTicket(@PathVariable String id) {
        ticketUsecases.deleteTicket(id);
    }

    @GetMapping("/get-one/{id}")
    public TicketDTO getById(@PathVariable String id) {
        return ticketUsecases.getById(id);
    }

    @GetMapping("/get-paginated")
    public List<TicketDTO> getMethodName(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy) {
        return ticketUsecases.getAllPaginated(pageNumber, size, sortBy);
    }

    @GetMapping("/get-by-status")
    public List<TicketDTO> getMethodName(@RequestParam Status status) {
        return filterTicketsUsecases.filterByStatus(status);
    }

    @GetMapping("/get-by-personId")
    public List<TicketDTO> getMethodName(@RequestParam String personId) {
        return filterTicketsUsecases.filterByPersonId(personId);
    }

    @GetMapping("/get-by-status-and-personId")
    public List<TicketDTO> getMethodName(@RequestParam Status status, @RequestParam String personId) {
        return filterTicketsUsecases.filterByStatusAndPersonId(status, personId);
    }

}
