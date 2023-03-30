package com.tngtech.architecture.api.controller;

import com.tngtech.architecture.api.model.Ticket;
import com.tngtech.architecture.api.model.TicketComment;
import com.tngtech.architecture.api.model.TicketStatus;
import com.tngtech.architecture.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket")
    public Collection<Ticket> getTicket(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            return ticketService.getAllTickets();
        }
        Ticket ticket = ticketService.getTicket(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return Collections.singletonList(ticket);
    }

    @PostMapping("/ticket")
    public Ticket createOrUpdateTicket(@RequestBody TicketCreate ticketCreate) {
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), ticketCreate.title(), ticketCreate.description(), ticketCreate.status());
        return ticketService.createTicket(ticket);
    }

    @GetMapping("/ticketStatusChange")
    public Ticket changeTicketStatus(@RequestParam("id") String id, @RequestParam("status") TicketStatus status) {
        Ticket ticket = ticketService.getTicket(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ticket.setStatus(status);
        return ticketService.updateTicket(ticket);
    }

    @PutMapping("/ticketUpdate")
    public Ticket ticketUpdate(@RequestBody TicketUpdate ticketUpdate) {
        Ticket ticket = ticketService.getTicket(ticketUpdate.id());
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ticket.addComment(new TicketComment(ticketUpdate.comment(), ticketUpdate.author()));
        ticketService.updateTicket(ticket);
        return ticket;
    }

    public record TicketUpdate(String id, String comment, String author) {
    }

    public record TicketCreate(String title, String description, TicketStatus status) {
    }

}
