package com.dvp.technical_test.domain.ticket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvp.technical_test.domain.ticket.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

}
