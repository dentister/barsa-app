package com.example.barsa.db.entities;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "ticket_change_events")
public class TicketChangeEvent {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="barsa_seq")
    @SequenceGenerator(name="barsa_seq", sequenceName="BARSA_SEQUENCE", allocationSize = 1)
    private Long id;

    @Column(name = "event_date")
    private Timestamp date;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_EVENT_USER"),name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TicketChangeEvent [id=" + id + ", date=" + date + ", user=" + user + "]";
    }
}
