package com.password.validator.passwordvalidator.modal;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PasswordResponse {

    HttpStatus status;
    String message;
}
