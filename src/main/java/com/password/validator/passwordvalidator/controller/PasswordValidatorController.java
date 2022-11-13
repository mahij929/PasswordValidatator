package com.password.validator.passwordvalidator.controller;

import com.password.validator.passwordvalidator.modal.PasswordResponse;
import com.password.validator.passwordvalidator.service.PasswordValidatorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/password")
public class PasswordValidatorController {

    Logger logger = LoggerFactory.getLogger(PasswordValidatorController.class);

    @Autowired
    private PasswordValidatorServiceImpl passwordValidatorService;

    @PostMapping("/validate")
    public ResponseEntity<?> validatePasswordController(@RequestBody String inputPassword){

        logger.info("Controller::Validate Password");

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(inputPassword);

        return ResponseEntity.status(passwordResponse.getStatus()).body(passwordResponse.getMessage());
    }
}
