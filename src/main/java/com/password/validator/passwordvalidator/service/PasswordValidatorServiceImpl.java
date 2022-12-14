package com.password.validator.passwordvalidator.service;

import com.password.validator.passwordvalidator.modal.PasswordResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PasswordValidatorServiceImpl implements PasswordvalidatorService {

    Logger logger = LoggerFactory.getLogger(PasswordValidatorServiceImpl.class);

    @Override
    public PasswordResponse validatePasswordService(String isValidPassword) {

        logger.info("Password to Validate is : {}", isValidPassword);

        String digitRegex = "^.*(?=.*[0-9])+.*$";
        Pattern digitsPatter = Pattern.compile(digitRegex);

        String lowercaseRegex = "^.*(?=.*[a-z])+.*$";
        Pattern lowercasePattern = Pattern.compile(lowercaseRegex);

        String uppercaseRegex = "^.*(?=.*[A-Z])+.*$";
        Pattern uppercasePattern = Pattern.compile(uppercaseRegex);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int counter = 0;

        jsonObject.put("counter", counter);

        PasswordResponse passwordResponse = new PasswordResponse();
        passwordResponse.setStatus(HttpStatus.OK);

        if(!(isValidPassword.trim()).isEmpty()) {
            if (lowercasePattern.matcher(isValidPassword).matches()) {
                if (isValidPassword.length() <= 9) {
                    logger.info("Length of password is less or equal to 8.");
                    jsonObject.put("counter", ++counter);
                    jsonArray.put("Length of password is less or equal to 8.");
                }
                if (!uppercasePattern.matcher(isValidPassword).matches()) {
                    logger.info("Password does not contain a uppercase character");
                    jsonObject.put("counter", ++counter);
                    jsonArray.put("Password does not contain a uppercase character.");
                }
                if (!digitsPatter.matcher(isValidPassword).matches()) {
                    logger.info("Password does not contain digit.");
                    jsonObject.put("counter", ++counter);
                    jsonArray.put("Password does not contain a digit.");
                }
            }else{
                logger.info("Password does not contain a lowercase character.");
                counter = 3;
                jsonObject.put("counter", 3);
                jsonArray.put("Password does not contain a lowercase character.");
            }
        }else{
            counter=3;
            logger.info("Input is empty");
            jsonObject.put("counter", 3);
            jsonArray.put("Password can not be empty");
        }

        if(counter>=2) {
            passwordResponse.setStatus(HttpStatus.BAD_REQUEST);
            jsonObject.put("data",jsonArray.toString());
            passwordResponse.setMessage(jsonArray.toString());

            return passwordResponse;
        }

        logger.info(String.valueOf(jsonObject.get("counter")));
        logger.info(jsonObject.toString());

        passwordResponse.setMessage("Password Accepted");

        return passwordResponse;
    }
}
