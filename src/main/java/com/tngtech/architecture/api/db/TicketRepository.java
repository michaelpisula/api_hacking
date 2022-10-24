package com.tngtech.architecture.api.db;

import com.tngtech.architecture.api.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {

    private final Map<String, Ticket> tickets = new HashMap<>();

    public void store(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String id) {
        return tickets.get(id);
    }

    public Collection<Ticket> getAllTickets() {
        return tickets.values();
    }
}
