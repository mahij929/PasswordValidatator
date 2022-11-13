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
        String noDigitPassword = "PasswordWithNoDigits";

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(noDigitPassword);

        System.out.println(passwordResponse.toString());

        assert (passwordResponse.getStatus().is4xxClientError());
        assert (passwordResponse.getMessage().contains("Password does not contain a digit"));
    }

    @Test
    void validatePasswordNoLowerCase(){
        String noLowercasePassword = "UPPERCASEPASSWORD";

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(noLowercasePassword);

        System.out.println(passwordResponse.toString());

        assert (passwordResponse.getStatus().is4xxClientError());
        assert (passwordResponse.getMessage().contains("Password does not contain a lowercase character"));
    }

    @Test
    void validatePasswordNoUppercase(){
        String noUppercasePassword = "lowercasepassword";

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(noUppercasePassword);

        System.out.println(passwordResponse.toString());

        assert (passwordResponse.getStatus().is4xxClientError());
        assert (passwordResponse.getMessage().contains("Password does not contain a uppercase character"));
    }

    @Test
    void validatePasswordNoUppercaseWithDigit(){
        String noUppercasePasswordWithDigit = "l0wercase";

        PasswordResponse passwordResponse = passwordValidatorService.validatePasswordService(noUppercasePasswordWithDigit);

        System.out.println(passwordResponse.toString());

        assert (passwordResponse.getStatus().is2xxSuccessful());
        assert (passwordResponse.getMessage().contains("Password Accepted"));
    }
}