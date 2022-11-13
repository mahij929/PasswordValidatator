package com.password.validator.passwordvalidator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/password")
public class PasswordValidatorController {

    Logger logger = LoggerFactory.getLogger(PasswordValidatorController.class);

    @PostMapping("/validate")
    public ResponseEntity<?> validatePasswordController(@RequestBody String inputPassword){

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
