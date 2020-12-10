package com.example.barsa.db.repositories;

import com.example.barsa.db.entities.TicketType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

}
