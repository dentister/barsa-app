package com.example.barsa.processors.validators;

import com.example.barsa.consts.TicketManagementOperation;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.xsd.ticket.input.TicketType;

import org.apache.commons.lang3.StringUtils;

public class TicketCudOperationValidator {
    private final TicketType ticket;
    private final TicketManagementOperation ticketOperation;

    public TicketCudOperationValidator(TicketType ticket, TicketManagementOperation ticketOperation) {
        this.ticket = ticket;
        this.ticketOperation = ticketOperation;
    }

    public void validate() throws BusinessValidationException {
        if (ticketOperation == TicketManagementOperation.ADD) {
            if (StringUtils.isEmpty(ticket.getTitle())) {
                throw new BusinessValidationException("Title is empty");
            }

            if (ticket.getTypeId() == null) {
                throw new BusinessValidationException("Tcket type is empty");
            }

            if (ticket.getAuthor() == null) {
                throw new BusinessValidationException("Author is empty");
            }

            if (ticket.getAssignee() == null) {
                throw new BusinessValidationException("Assignee is empty");
            }
        }

        if (ticketOperation == TicketManagementOperation.MOD || ticketOperation == TicketManagementOperation.DEL) {
            if (ticket.getId() == null) {
                throw new BusinessValidationException("Ticket ID is empty");
            }
        }
    }
}