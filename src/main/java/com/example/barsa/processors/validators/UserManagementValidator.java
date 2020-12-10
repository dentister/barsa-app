package com.example.barsa.processors.validators;

import com.example.barsa.consts.UserManagementOperation;
import com.example.barsa.exceptions.BusinessValidationException;
import com.example.barsa.xsd.user.input.UserType;

import org.apache.commons.lang3.StringUtils;

public class UserManagementValidator {
    private UserType userDto;
    private UserManagementOperation operation;

    public UserManagementValidator(UserType userDto, UserManagementOperation operation) {
        this.userDto = userDto;
        this.operation = operation;
    }

    public void validate() throws BusinessValidationException {
        if (operation == UserManagementOperation.ADD || operation == UserManagementOperation.MOD) {
            if (StringUtils.isEmpty(userDto.getFirstname())) {
                throw new BusinessValidationException("Empty firstname.");
            }

            if (StringUtils.isEmpty(userDto.getLastname())) {
                throw new BusinessValidationException("Empty lastname.");
            }

            if (StringUtils.isEmpty(userDto.getPassword())) {
                throw new BusinessValidationException("Empty password.");
            }
        }

        if (StringUtils.isEmpty(userDto.getLogin())) {
            throw new BusinessValidationException("Empty login.");
        }
    }
}
