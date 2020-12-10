package com.example.barsa.services.rest;

import com.example.barsa.db.entities.Ticket;
import com.example.barsa.dto.TicketDto;
import com.example.barsa.services.data.TicketService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("BarsaREST")
public class TicketDataRestController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets/{id}")
    public TicketDto getTicket(@PathVariable Long id) {
        System.out.println("TicketDataRestController.getTicket()");

        Ticket ticket = ticketService.getTicketById(id);

        return ticket == null ? null : new TicketDto(ticket);
    }

    @RequestMapping("/tickets")
    public List<TicketDto> getTickets(@RequestParam int page, @RequestParam int pageSize) {
        System.out.println("TicketDataRestController.getTickets() [page=" + page + ", pageSize=" + pageSize + "]");

        List<Ticket> list = ticketService.getTickets(page, pageSize);

        return list.stream().map(obj -> new TicketDto(obj)).collect(Collectors.toList());
    }

    @PostMapping(value = "/tickets/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TicketDto> searchByParams(@RequestBody TicketDto knownParams) {
        System.out.println("TicketDataRestController.searchByParams() [knownParams=" + knownParams + "]");

        List<Ticket> list = ticketService.findTicketsByParams(knownParams);

        return list.stream().map(obj -> new TicketDto(obj)).collect(Collectors.toList());
    }
}
