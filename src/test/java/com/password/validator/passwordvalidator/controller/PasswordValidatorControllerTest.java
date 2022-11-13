package com.password.validator.passwordvalidator.controller;

import com.password.validator.passwordvalidator.modal.PasswordResponse;
import com.password.validator.passwordvalidator.service.PasswordValidatorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PasswordValidatorControllerTest {

    @InjectMocks
    private PasswordValidatorController passwordValidatorController;

    @Mock
    private PasswordValidatorServiceImpl passwordValidatorService;

    private PasswordResponse passwordResponse;

    @BeforeEach
    void setUp() {
        this.passwordResponse = new PasswordResponse();
        passwordResponse.setStatus(HttpStatus.OK);
        passwordResponse.setMessage("Password Accepted");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validatePasswordController() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(passwordValidatorService.validatePasswordService(any(String.class))).thenReturn(passwordResponse);
        ResponseEntity<?> response = passwordValidatorController.validatePasswordController("SomeStrings");

        System.out.println(response.toString());

        assert (response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody(), "Password Accepted");
    }
}