package com.example.barsa.processors.handlers;

import com.example.barsa.consts.UserManagementOperation;
import com.example.barsa.db.entities.User;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.processors.validators.UserManagementValidator;
import com.example.barsa.services.data.UserService;
import com.example.barsa.xsd.user.input.DoUserOperation;
import com.example.barsa.xsd.user.input.UserOperationType;
import com.example.barsa.xsd.user.input.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
@Component
public class UserManagementHandler {

    @Autowired
    private UserService userService;

//    public UserManagementHandler(UserService userService) {
//        this.userService = userService;
//    }

    public void execute(DoUserOperation doUserOperation) throws BusinessValidationException {
        System.out.println("UserManagementHandler.execute() started");

        if (doUserOperation != null && !CollectionUtils.isEmpty(doUserOperation.getRecord())) {
            for (UserOperationType record : doUserOperation.getRecord()) {
                UserManagementOperation operation = UserManagementOperation.valueOf(record.getOperation());

                new UserManagementValidator(record.getUser(), operation).validate();

                switch (operation) {
                    case ADD:
                        createUser(record.getUser());

                        break;
                    case MOD:
                        modifyUser(record.getUser());

                        break;
                    case DEL:
                        deleteUser(record.getUser());

                        break;
                    default:
                        break;
                }
            }
        }

        System.out.println("UserManagementHandler.execute() finished");
    }

    private void createUser(UserType userDto) {
        System.out.println("UserManagementProcessor.createUser()");

        User userEntity = new User();

        userEntity.setLogin(userDto.getLogin());
        userEntity.setFirstName(userDto.getFirstname());
        userEntity.setLastName(userDto.getLastname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        userService.createUser(userEntity);
    }

    private void modifyUser(UserType userDto) throws BusinessValidationException {
        System.out.println("UserManagementProcessor.modifyUser()");

        User userEntity = userService.getUserByLogin(userDto.getLogin());

        if (userEntity == null) {
            throw new BusinessValidationException("Object not exists with login: " + userDto.getLogin());
        }

        userEntity.setLogin(userDto.getLogin());
        userEntity.setFirstName(userDto.getFirstname());
        userEntity.setLastName(userDto.getLastname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        userService.updateUser(userEntity);
    }

    private void deleteUser(UserType userDto) throws BusinessValidationException {
        System.out.println("UserManagementProcessor.deleteUser()");

        User userEntity = userService.getUserByLogin(userDto.getLogin());

        if (userEntity == null) {
            throw new BusinessValidationException("Object not exists with login: " + userDto.getLogin());
        }

        userService.deleteUser(userEntity);
    }

}
