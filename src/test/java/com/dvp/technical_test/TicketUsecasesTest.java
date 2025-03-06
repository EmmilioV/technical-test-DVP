package com.dvp.technical_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dvp.technical_test.application.dtos.ticket.TicketConverter;
import com.dvp.technical_test.application.dtos.ticket.TicketDTO;
import com.dvp.technical_test.application.exception.BusinessException;
import com.dvp.technical_test.application.usecases.TicketUsecases;
import com.dvp.technical_test.domain.person.entity.Person;
import com.dvp.technical_test.domain.person.repository.PersonRepository;
import com.dvp.technical_test.domain.ticket.entity.Status;
import com.dvp.technical_test.domain.ticket.entity.Ticket;
import com.dvp.technical_test.domain.ticket.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketUsecasesTest {
    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private TicketConverter ticketConverter;

    @InjectMocks
    private TicketUsecases ticketUsecases;

    TicketDTO ticketDTO;
    Ticket ticket;
    TicketDTO savedTicketDTO;
    Ticket savedTicket;

    @BeforeEach
    void setUp() {
        ticketDTO = TicketDTO.builder()
                .description("test")
                .personId(UUID.randomUUID().toString())
                .status(Status.ABIERTO)
                .build();

        ticket = Ticket.builder()
                .description(ticketDTO.getDescription())
                .personId(UUID.fromString(ticketDTO.getPersonId()))
                .status(ticketDTO.getStatus())
                .build();

        savedTicket = Ticket.builder()
                .id(UUID.randomUUID())
                .description(ticket.getDescription())
                .personId(ticket.getPersonId())
                .status(ticket.getStatus())
                .build();

        savedTicketDTO = TicketDTO.builder()
                .id(savedTicket.getId().toString())
                .description(savedTicket.getDescription())
                .personId(savedTicket.getPersonId().toString())
                .status(savedTicket.getStatus())
                .build();
    }

    @Test
    void createTicket_ShouldReturnSavedTicketDTO() {
        ticketDTO.setCreatedDate(LocalDateTime.now());
        ticketDTO.setUpdatedDate(LocalDateTime.now());

        savedTicket.setCreatedDate(LocalDateTime.now());
        savedTicket.setUpdatedDate(LocalDateTime.now());

        when(personRepository.findById(UUID.fromString(ticketDTO.getPersonId())))
                .thenReturn(Optional.of(Person.builder().build()));
        when(ticketConverter.toTicketFromTicketDTO(ticketDTO)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(savedTicket);
        when(ticketConverter.toTicketDTOFromTicket(savedTicket)).thenReturn(savedTicketDTO);

        TicketDTO result = ticketUsecases.createTicket(ticketDTO);

        verify(personRepository).findById(UUID.fromString(ticketDTO.getPersonId()));
        verify(ticketConverter).toTicketFromTicketDTO(ticketDTO);
        verify(ticketRepository).save(ticket);
        verify(ticketConverter).toTicketDTOFromTicket(savedTicket);

        assertNotNull(result);
        assertEquals(savedTicketDTO, result);
    }

    @Test
    void createTicket_ShouldGenerateException_personNotExists() {
        when(personRepository.findById(UUID.fromString(ticketDTO.getPersonId()))).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class, () -> ticketUsecases.createTicket(ticketDTO));

        verify(personRepository).findById(UUID.fromString(ticketDTO.getPersonId()));
        verify(ticketConverter, never()).toTicketFromTicketDTO(ticketDTO);
        verify(ticketRepository, never()).save(ticket);
        verify(ticketConverter, never()).toTicketDTOFromTicket(savedTicket);
    }
}
