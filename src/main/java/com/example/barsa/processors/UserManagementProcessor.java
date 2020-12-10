package com.example.barsa.processors;

import com.example.barsa.core.entities.consts.Result;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.processors.handlers.UserManagementHandler;
import com.example.barsa.services.ws.producers.UserManagementNotifications;
import com.example.barsa.xsd.user.input.DoUserOperation;
import com.example.barsa.xsd.user.output.Notification;
import com.example.barsa.xsd.user.output.NotificationType;
import com.example.barsa.xsd.user.output.ServiceInfoType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManagementProcessor {
    @Autowired
    private UserManagementHandler handler;

    @Autowired
    private UserManagementNotifications outputInterface;

    public void execute(DoUserOperation doUserOperation) throws BusinessValidationException {
        System.out.println("UserManagementProcessor.execute() started");

        Notification notification;

        try {
            handler.execute(doUserOperation);

            notification = createNotification(null);
        } catch (Exception e) {
            e.printStackTrace();

            notification = createNotification(e);
        }

        outputInterface.sendNotification(notification);

        System.out.println("UserManagementProcessor.execute() finished");
    }


    private Notification createNotification(Exception e) {
        Notification notification = new Notification();
        ServiceInfoType serviceInfo = new ServiceInfoType();
        NotificationType notifyRecord = new NotificationType();

        serviceInfo.setSourceSystem("SOURCE");
        serviceInfo.setTargetSystem("TARGET");
        serviceInfo.setTransactionId("sadfasdfasdf");

        if (e == null) {
            notifyRecord.setCode(Result.OK.name());
            notifyRecord.setMessage(Result.OK.name());
        } else {
            notifyRecord.setCode(Result.KO.name());
            notifyRecord.setMessage(e.getMessage());
        }

        notification.setServiceInfo(serviceInfo);
        notification.getRecord().add(notifyRecord);

        return notification;
    }
}
