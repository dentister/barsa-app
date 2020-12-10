package com.example.barsa.processors.handlers;

import com.example.barsa.consts.TicketManagementOperation;
import com.example.barsa.core.entities.consts.Status;
import com.example.barsa.db.entities.Ticket;
import com.example.barsa.db.entities.TicketStatus;
import com.example.barsa.db.entities.User;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.processors.validators.TicketCudOperationValidator;
import com.example.barsa.services.data.TicketService;
import com.example.barsa.services.data.UserService;
import com.example.barsa.xsd.ticket.input.CudTicketOperationType;
import com.example.barsa.xsd.ticket.input.TicketType;
import com.example.barsa.xsd.ticket.input.UserInfo;

import java.sql.Timestamp;

public class TicketCudOperationHandler {
    private final TicketService ticketService;
    private final UserService userService;

    public TicketCudOperationHandler(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    public void handle(CudTicketOperationType ticketOperation, String srcSys) throws BusinessValidationException {
        System.out.println("TicketCudOperationHandler.handle() started");

        TicketManagementOperation operationType = TicketManagementOperation.valueOf(ticketOperation.getOperation());

        new TicketCudOperationValidator(ticketOperation.getTicket(), operationType).validate();

        switch (operationType) {
            case ADD:
                createTicket(ticketOperation.getTicket(), srcSys);

                break;
            case MOD:
                modifyTicket(ticketOperation.getTicket(), srcSys);

                break;
            case DEL:
                deleteTicket(ticketOperation.getTicket());

                break;
            default:
                break;
        }

        System.out.println("TicketCudOperationHandler.handle() finished");
    }

    private Ticket createTicket(TicketType ticketDto, String srcSys) {
        System.out.println("TicketCudOperationHandler.createTicket()");

        UserInfo authorDto = ticketDto.getAuthor();
        UserInfo assigneeDto = ticketDto.getAssignee();

        User eventUser = getSystemUser(srcSys);
        User author = authorDto.getId() != null ? userService.getUserById(authorDto.getId()) : userService.getUserByLogin(authorDto.getLogin());
        User assignee = assigneeDto.getId() != null ? userService.getUserById(assigneeDto.getId()) : userService.getUserByLogin(assigneeDto.getLogin());
        TicketStatus statusEntity = ticketService.getTicketStatus(Status.OPEN);
        com.example.barsa.db.entities.TicketType typeEntity = ticketService.getTicketType(ticketDto.getTypeId());

        Ticket ticketEntity = new Ticket();

        ticketEntity.setTitle(ticketDto.getTitle());
        ticketEntity.setDescription(ticketDto.getDescription());
        ticketEntity.setAssignee(assignee);
        ticketEntity.setAuthor(author);
        ticketEntity.setStatus(statusEntity);
        ticketEntity.setType(typeEntity);
        ticketEntity.setCreatedEventId(ticketService.createChangeEvent(eventUser, new Timestamp(System.currentTimeMillis())));
        ticketEntity.setModifiedEvent(ticketService.createChangeEvent(eventUser, new Timestamp(System.currentTimeMillis())));

        return ticketService.createTicket(ticketEntity);
    }

    private Ticket modifyTicket(TicketType ticketDto, String srcSys) {
        System.out.println("TicketCudOperationHandler.modifyTicket()");

        Ticket ticketEntity = ticketService.getTicketById(ticketDto.getId());

        if (ticketDto.getTitle() != null) {
            ticketEntity.setTitle(ticketDto.getTitle());
        }

        if (ticketDto.getDescription() != null) {
            ticketEntity.setDescription(ticketDto.getDescription());
        }

        if (ticketDto.getAssignee() != null) {
            UserInfo userDto = ticketDto.getAssignee();
            User userEntity = userDto.getId() != null ? userService.getUserById(userDto.getId()) : userService.getUserByLogin(userDto.getLogin());

            ticketEntity.setAssignee(userEntity);
        }

        if (ticketDto.getAuthor() != null) {
            UserInfo userDto = ticketDto.getAuthor();
            User userEntity = userDto.getId() != null ? userService.getUserById(userDto.getId()) : userService.getUserByLogin(userDto.getLogin());

            ticketEntity.setAuthor(userEntity);
        }

        if (ticketDto.getTypeId() != null) {
            com.example.barsa.db.entities.TicketType typeEntity = ticketService.getTicketType(ticketDto.getTypeId());

            ticketEntity.setType(typeEntity);
        }

        ticketEntity.setModifiedEvent(ticketService.createChangeEvent(getSystemUser(srcSys), new Timestamp(System.currentTimeMillis())));

        return ticketService.updateTicket(ticketEntity);
    }

    private void deleteTicket(TicketType ticketDto) {
        System.out.println("TicketCudOperationHandler.deleteTicket()");

        ticketService.deleteTicket(ticketDto.getId());
    }

    private User getSystemUser(String sourceSystem) {
        User user = userService.getUserByLogin(sourceSystem);

        if (user == null) {
            user = userService.createUser(new User(sourceSystem, null, null, null, null));
        }

        return user;
    }
}