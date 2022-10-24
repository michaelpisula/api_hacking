package com.tngtech.architecture.api.model;

import java.util.LinkedList;
import java.util.List;

public class Ticket {
    private final String id;
    private final String title;
    private final String description;

    private TicketStatus status;

    private final List<TicketComment> comments;

    public Ticket(String id, String title, String description, TicketStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        comments = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public List<TicketComment> getComments() {
        return comments;
    }

    public void addComment(TicketComment comment) {
        comments.add(comment);
    }
}
