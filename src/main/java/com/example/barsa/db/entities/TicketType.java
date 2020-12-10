package com.example.barsa.db.entities;

import com.example.barsa.core.entities.consts.Type;

import javax.persistence.*;

@Entity
@Table(name = "TICKET_TYPES")
public class TicketType {
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

    public Type value() {
        return Type.getValue(id);
    }
}
