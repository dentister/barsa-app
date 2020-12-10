package com.example.barsa.services.data;

import com.example.barsa.core.entities.consts.Status;
import com.example.barsa.core.entities.consts.Type;
import com.example.barsa.db.entities.*;
import com.example.barsa.db.repositories.*;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private TicketChangeEventRepository changeEventRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    public void deleteTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        deleteTicket(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getTickets(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        return ticketRepository.findAll(pageable).toList();
    }

    public List<Ticket> findTicketsByParams(Object knownParams) {
        Ticket ticket = new Ticket();

//        example.setAssignee(assignee);
//        example.setAuthor(author);
//        example.setClosedEvent(closedEvent);
//        example.setCreatedEventId(createdEvent);
//        example.setDescription(description);
//        example.setModifiedEvent(modifiedEvent);
//        example.setResolvedEvent(resolvedEvent);
//        example.setTitle(title);
//        example.setType(type);

        Example<Ticket> example = Example.of(ticket);

        return ticketRepository.findAll(example);
    }

    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    public TicketType getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id).orElse(null);
    }

    public List<TicketStatus> getAllTicketStatuses() {
        return ticketStatusRepository.findAll();
    }

    public TicketStatus getTicketStatusById(Long id) {
        return ticketStatusRepository.findById(id).orElse(null);
    }

    public TicketStatus getTicketStatus(Status status) {
        return ticketStatusRepository.findById(status.getId()).orElse(null);
    }

    public TicketType getTicketType(Type type) {
        return getTicketType(type.getId());
    }

    public TicketType getTicketType(Long id) {
        return ticketTypeRepository.findById(id).orElse(null);
    }

    public TicketChangeEvent createChangeEvent(User user, Timestamp date) {
        TicketChangeEvent event = new TicketChangeEvent();

        event.setUser(user);
        event.setDate(date);

        return changeEventRepository.save(event);
    }
}
