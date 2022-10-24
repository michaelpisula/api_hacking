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
import java.util.List;
import java.util.UUID;

@RestController
public class TicketsController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/tickets")
    public Collection<Ticket> getAllTickets() {
            return ticketService.getAllTickets();
        }

    @GetMapping("/tickets/{id}")
    public Ticket getTicket(@PathVariable String id) {
        Ticket ticket = ticketService.getTicket(id);
        if(ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ticket;
    }

    @PostMapping("/tickets")
    public Ticket createTicket(@RequestBody TicketCreate ticketCreate) {
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), ticketCreate.title(), ticketCreate.description(), ticketCreate.status());
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/tickets/{id}/status")
    public Ticket changeTicketStatus(@PathVariable( "id") String id, @RequestBody TicketStatusUpdate status) {
        Ticket ticket = ticketService.getTicket(id);
        ticket.setStatus(status.status());
        return ticketService.updateTicket(ticket);
    }

    @GetMapping("/tickets/{id}/comments")
    public List<TicketComment> getTicketComments(@PathVariable( "id") String id) {
        Ticket ticket = ticketService.getTicket(id);
        return ticket.getComments();
    }

    @PostMapping("/tickets/{id}/comments")
    public Ticket addTicketComment(@PathVariable( "id") String id, @RequestBody TicketCommentCreate ticketCommentCreate) {
        Ticket ticket = ticketService.getTicket(id);
        ticket.addComment(new TicketComment(ticketCommentCreate.comment(), ticketCommentCreate.author()));
        ticketService.updateTicket(ticket);
        return ticket;
    }

    public record TicketCommentCreate(String comment, String author) {}
    public record TicketCreate(String title, String description, TicketStatus status) {}
    public record TicketStatusUpdate(TicketStatus status) {}

}
