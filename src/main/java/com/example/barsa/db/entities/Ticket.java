package com.example.barsa.db.entities;

import javax.persistence.*;

@Entity
@Table(name="TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="barsa_seq")
    @SequenceGenerator(name="barsa_seq", sequenceName="BARSA_SEQUENCE", allocationSize = 1)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_AUTHOR"), name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_ASSIGNEE"), name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_STATUS"), name = "ticket_status_id")
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_TYPE"), name = "ticket_type_id")
    private TicketType type;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CREATED"), name = "created", nullable = true)
    private TicketChangeEvent createdEvent;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_MODIFIED"), name = "modified", nullable = true)
    private TicketChangeEvent modifiedEvent;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_RESOLVED"), name = "resolved", nullable = true)
    private TicketChangeEvent resolvedEvent;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CLOSED"), name = "closed", nullable = true)
    private TicketChangeEvent closedEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public TicketChangeEvent getCreatedEvent() {
        return createdEvent;
    }

    public void setCreatedEventId(TicketChangeEvent createdEvent) {
        this.createdEvent = createdEvent;
    }

    public TicketChangeEvent getModifiedEvent() {
        return modifiedEvent;
    }

    public void setModifiedEvent(TicketChangeEvent modifiedEvent) {
        this.modifiedEvent = modifiedEvent;
    }

    public TicketChangeEvent getResolvedEvent() {
        return resolvedEvent;
    }

    public void setResolvedEvent(TicketChangeEvent resolvedEvent) {
        this.resolvedEvent = resolvedEvent;
    }

    public TicketChangeEvent getClosedEvent() {
        return closedEvent;
    }

    public void setClosedEvent(TicketChangeEvent closedEvent) {
        this.closedEvent = closedEvent;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author
                + ", assignee=" + assignee + ", statusId=" + status + ", typeId=" + type + ", createdEventId="
                + createdEvent + ", modifiedEventId=" + modifiedEvent + ", resolvedEventId=" + resolvedEvent
                + ", closedEventId=" + closedEvent + "]";
    }
}
