package com.example.barsa.db.repositories;

import com.example.barsa.db.entities.TicketStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {

}
