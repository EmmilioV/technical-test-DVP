package com.dvp.technical_test.domain.ticket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvp.technical_test.domain.ticket.entity.Status;
import com.dvp.technical_test.domain.ticket.entity.Ticket;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByStatus(Status status);

    List<Ticket> findByPersonId(UUID personId);

    List<Ticket> findByStatusAndPersonId(Status status, UUID PersonId);
}
