package com.password.validator.passwordvalidator.service;

import com.password.validator.passwordvalidator.modal.PasswordResponse;

public interface PasswordvalidatorService {

    static String uppercaseRegex = "^.*(?=.*[A-Z])+.*$";
    static String lowercaseRegex = "^.*(?=.*[a-z])+.*$";
    static String digitRegex = "^.*(?=.*[0-9])+.*$";

    public PasswordResponse validatePasswordService(String isValidPassword);
}
