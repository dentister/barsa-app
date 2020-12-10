package com.example.barsa.db.repositories;

import com.example.barsa.db.entities.TicketChangeEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketChangeEventRepository extends JpaRepository<TicketChangeEvent, Long> {

}
