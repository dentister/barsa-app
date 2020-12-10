package com.example.barsa.processors;

import com.example.barsa.core.entities.consts.Result;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.processors.handlers.TicketCudOperationHandler;
import com.example.barsa.services.data.TicketService;
import com.example.barsa.services.data.UserService;
import com.example.barsa.services.ws.producers.TicketManagementNotifications;
import com.example.barsa.xsd.ticket.input.DoTicketOperation;
import com.example.barsa.xsd.ticket.input.DoTicketOperationType;
import com.example.barsa.xsd.ticket.output.Notification;
import com.example.barsa.xsd.ticket.output.NotificationType;
import com.example.barsa.xsd.ticket.output.ServiceInfoType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Scope("prototype")
public class TicketManagementProcessor {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketManagementNotifications outputInterface;

    public void execute(DoTicketOperation doTicketOperation) throws BusinessValidationException {
        System.out.println("TicketManagementProcessor.execute()");

        try {
            com.example.barsa.xsd.ticket.input.ServiceInfoType serviceInfo = doTicketOperation.getServiceInfo();

            if (doTicketOperation != null && !CollectionUtils.isEmpty(doTicketOperation.getRecord())) {
                for (DoTicketOperationType record : doTicketOperation.getRecord()) {
                    if (record.getCudTicketOperation() != null) {
                        new TicketCudOperationHandler(ticketService, userService).handle(record.getCudTicketOperation(), serviceInfo.getSourceSystem());
                    }
                }
            }

            outputInterface.sendNotification(createNotification(Result.OK, null));
        } catch (Exception e) {
            e.printStackTrace();

            outputInterface.sendNotification(createNotification(Result.KO, e.getMessage()));
        }
    }

    private Notification createNotification(Result result, String msg) {
        Notification notification = new Notification();
        ServiceInfoType serviceInfo = new ServiceInfoType();
        NotificationType notifyRecord = new NotificationType();

        serviceInfo.setSourceSystem("SOURCE");
        serviceInfo.setTargetSystem("TARGET");
        serviceInfo.setTransactionId("sadfasdfasdf");

        if (result == Result.OK) {
            notifyRecord.setCode("000");
            notifyRecord.setMessage("All is ok");
        } else {
            notifyRecord.setCode("001");
            notifyRecord.setMessage(msg);
        }

        notification.setServiceInfo(serviceInfo);
        notification.getRecord().add(notifyRecord);

        return notification;
    }
}
