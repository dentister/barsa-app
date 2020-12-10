package com.example.barsa.core.bussiness;

import com.example.barsa.consts.TicketManagementOperation;
import com.example.barsa.db.entities.Ticket;
import com.example.barsa.db.entities.TicketStatus;
import com.example.barsa.services.data.TicketService;
import com.example.barsa.xsd.ticket.input.LifeTicketOperationType;

import org.springframework.stereotype.Component;

@Component
public class TicketLifecycle {
    private final TicketService ticketService;

    public TicketLifecycle(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public void updateTicket(LifeTicketOperationType ticketOperation) {
        TicketManagementOperation operation = TicketManagementOperation.valueOf(ticketOperation.getOperation());
        Long statusId = ticketOperation.getStatusId();
        ticketOperation.getTicketId();

        Ticket ticket = ticketService.getTicketById(ticketOperation.getTicketId());

        switch (operation) {
            case GO_STATUS:
                TicketStatus status = ticketService.getTicketStatusById(statusId);
                ticket.setStatus(status);
                ticketService.updateTicket(ticket);

                break;

            default:
                break;
        }
    }
}
