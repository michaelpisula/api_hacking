package com.tngtech.architecture.api.service;

import com.tngtech.architecture.api.db.TicketRepository;
import com.tngtech.architecture.api.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        ticketRepository.store(ticket);

        return ticket;
    }

    public Ticket updateTicket(Ticket ticket) {
        ticketRepository.store(ticket);
        return ticket;
    }

    public Ticket getTicket(String id) {
        return ticketRepository.getTicket(id);
    }

    public Collection<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }
}
