package com.dvp.technical_test.application.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dvp.technical_test.application.dtos.ticket.TicketConverter;
import com.dvp.technical_test.application.dtos.ticket.TicketDTO;
import com.dvp.technical_test.application.exception.BusinessException;
import com.dvp.technical_test.domain.person.repository.PersonRepository;
import com.dvp.technical_test.domain.ticket.entity.Ticket;
import com.dvp.technical_test.domain.ticket.repository.TicketRepository;

@Service
public class TicketUsecases {
    private final TicketRepository ticketRepository;
    private final PersonRepository personRepository;
    private final TicketConverter ticketConverter;

    public TicketUsecases(
            TicketRepository ticketRepository,
            PersonRepository personRepository,
            TicketConverter ticketConverter) {
        this.ticketRepository = ticketRepository;
        this.ticketConverter = ticketConverter;
        this.personRepository = personRepository;
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        personRepository.findById(UUID.fromString(ticketDTO.getPersonId()))
                .orElseThrow(() -> new BusinessException("the person to assign ticket does not exist"));

        ticketDTO.setCreatedDate(LocalDateTime.now());
        ticketDTO.setUpdatedDate(LocalDateTime.now());

        Ticket ticketSaved = ticketRepository.save(ticketConverter.toTicketFromTicketDTO(ticketDTO));
        return ticketConverter.toTicketDTOFromTicket(ticketSaved);
    }

    public TicketDTO update(TicketDTO ticketDTO) {

        ticketDTO.setUpdatedDate(LocalDateTime.now());

        Ticket ticket = ticketConverter.toTicketFromTicketDTO(ticketDTO);

        ticketRepository.findById(ticket.getId())
                .orElseThrow(() -> new IllegalArgumentException("ticket not exists"));

        personRepository.findById(ticket.getPersonId())
                .orElseThrow(() -> new BusinessException("the person to assign ticket does not exist"));

        return ticketConverter.toTicketDTOFromTicket(ticketRepository.save(ticket));
    }

    public void deleteTicket(String ticketId) {
        ticketRepository.deleteById(UUID.fromString(ticketId));
    }

    public TicketDTO getById(String ticketId) {
        return ticketRepository.findById(UUID.fromString(ticketId))
                .map(ticketConverter::toTicketDTOFromTicket)
                .orElseThrow(() -> new IllegalArgumentException("ticket not found"));
    }

    public List<TicketDTO> getAllPaginated(int pageNumber, int size, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortBy));

        return ticketRepository.findAll(pageable)
                .stream()
                .map(ticketConverter::toTicketDTOFromTicket)
                .toList();
    }
}
