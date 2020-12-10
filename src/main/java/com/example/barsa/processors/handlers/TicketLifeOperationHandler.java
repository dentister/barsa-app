package com.example.barsa.processors.handlers;

import com.example.barsa.core.bussiness.TicketLifecycle;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.services.data.TicketService;
import com.example.barsa.xsd.ticket.input.LifeTicketOperationType;

public class TicketLifeOperationHandler {
    private final TicketLifecycle ticketLifecycle;

    public TicketLifeOperationHandler(TicketService ticketService) {
        this.ticketLifecycle = new TicketLifecycle(ticketService);
    }

    public void handle(LifeTicketOperationType ticketOperation, String srcSys) throws BusinessValidationException {
        System.out.println("TicketLifeOperationHandler.handle() started");

        ticketLifecycle.updateTicket(ticketOperation);

        System.out.println("TicketCudOperationHandler.handle() finished");
    }
}