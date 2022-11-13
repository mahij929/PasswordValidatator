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

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int counter = 0;

        jsonObject.put("counter", counter);

        PasswordResponse passwordResponse = new PasswordResponse();
        passwordResponse.setStatus(HttpStatus.OK);

        if(!(isValidPassword.trim()).isEmpty()) {
            if (isValidPassword.length() <= 9) {
                logger.info("Size of password is : {}", isValidPassword.length());
                jsonObject.put("counter", ++counter);
                jsonArray.put("Length of password is less or equal to 8.");
            }if (!digitsPatter.matcher(isValidPassword).matches()) {
                logger.info("Digit Value {}" ,String.valueOf(digitsPatter.matcher(isValidPassword).matches()));
                jsonObject.put("counter", ++counter);
                jsonArray.put("Password does not contain a digit");
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

        return passwordResponse;
    }
}
