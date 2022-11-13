package com.password.validator.passwordvalidator.service;

import com.password.validator.passwordvalidator.modal.PasswordResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordValidatorServiceImplTest {

    @Autowired
    private PasswordValidatorServiceImpl passwordValidatorService;

    @Test
    void validatePasswordServiceLengthFail() {

        String shortPassword = "sh0rt";

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(shortPassword);

        System.out.println(passwordResponse);

        assert (passwordResponse.getStatus().is4xxClientError());
        assert (passwordResponse.getMessage().contains("Length of password is less or equal to 8."));
    }

    @Test
    void validatePasswordServiceEmpty(){
        String emptyPassword = "   ";
        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(emptyPassword);

        System.out.println(passwordResponse.toString());

        assert (passwordResponse.getStatus().is4xxClientError());
        assert (passwordResponse.getMessage().contains("Password can not be empty"));
    }

    @Test
    void validatePasswordFailDigit(){
        String noDigitPassword = "hellooooo";

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(noDigitPassword);

        System.out.println(passwordResponse.toString());

        assert (passwordResponse.getStatus().is4xxClientError());
        assert (passwordResponse.getMessage().contains("Password does not contain a digit"));
    }
}