package com.example.barsa.dto;

import com.example.barsa.core.entities.consts.Status;
import com.example.barsa.core.entities.consts.Type;
import com.example.barsa.db.entities.Ticket;
import com.example.barsa.db.entities.TicketChangeEvent;
import com.example.barsa.db.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketDto {
    private final Long id;
    private final String title;
    private final String description;
    private final LightUserDto author;
    private final LightUserDto assignee;
    private final Status status;
    private final Type type;
    private final TicketEventDto createdEvent;
    private final TicketEventDto modifiedEvent;
    private final TicketEventDto resolvedEvent;
    private final TicketEventDto closedEvent;

    public TicketDto(Ticket ticketEntity) {
        this.id = ticketEntity.getId();
        this.title = ticketEntity.getTitle();
        this.description = ticketEntity.getDescription() != null ? ticketEntity.getDescription() : null;
        this.assignee = new LightUserDto(ticketEntity.getAssignee());
        this.author = new LightUserDto(ticketEntity.getAuthor());
        this.status = ticketEntity.getStatus().value();
        this.type = ticketEntity.getType().value();
        this.createdEvent = ticketEntity.getCreatedEvent() != null ? new TicketEventDto(ticketEntity.getCreatedEvent()) : null;
        this.modifiedEvent = ticketEntity.getModifiedEvent() != null ? new TicketEventDto(ticketEntity.getModifiedEvent()) : null;
        this.resolvedEvent = ticketEntity.getResolvedEvent() != null ? new TicketEventDto(ticketEntity.getResolvedEvent()) : null;
        this.closedEvent = ticketEntity.getClosedEvent() != null? new TicketEventDto(ticketEntity.getClosedEvent()) : null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LightUserDto getAuthor() {
        return author;
    }

    public LightUserDto getAssignee() {
        return assignee;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public TicketEventDto getCreatedEvent() {
        return createdEvent;
    }

    public TicketEventDto getModifiedEvent() {
        return modifiedEvent;
    }

    public TicketEventDto getResolvedEvent() {
        return resolvedEvent;
    }

    public TicketEventDto getClosedEvent() {
        return closedEvent;
    }

    @Override
    public String toString() {
        return "TicketDto [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author
                + ", assignee=" + assignee + ", status=" + status + ", type=" + type + ", createdEvent=" + createdEvent
                + ", modifiedEvent=" + modifiedEvent + ", resolvedEvent=" + resolvedEvent + ", closedEvent="
                + closedEvent + "]";
    }

    public class LightUserDto {
        private final Long id;
        private final String login;

        private LightUserDto(User user) {
            this.id = user.getId();
            this.login = user.getLogin();
        }

        public Long getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        @Override
        public String toString() {
            return "LightUserDto [id=" + id + ", login=" + login + "]";
        }
    }

    public class TicketEventDto {
        private final Long id;
        private final Date when;
        private final LightUserDto by;

        private TicketEventDto(TicketChangeEvent ticketEvent) {
            this.id = ticketEvent.getId();
            this.when = ticketEvent.getDate();
            this.by = new LightUserDto(ticketEvent.getUser());
        }

        public Long getId() {
            return id;
        }

        public Date getWhen() {
            return when;
        }

        public LightUserDto getBy() {
            return by;
        }

        @Override
        public String toString() {
            return "TicketEventDto [id=" + id + ", when=" + when + ", by=" + by + "]";
        }
    }
}
