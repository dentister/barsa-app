package com.example.barsa.db.entities;

import com.example.barsa.core.entities.consts.Status;

import javax.persistence.*;

@Entity
@Table(name = "TICKET_STATUSES")
public class TicketStatus {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status value() {
        return Status.getValue(id);
    }

    @Override
    public String toString() {
        return "TicketType [id=" + id + ", name=" + name + ", description=" + description + "]";
    }
}
