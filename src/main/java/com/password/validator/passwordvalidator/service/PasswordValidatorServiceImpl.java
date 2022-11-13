package com.password.validator.passwordvalidator.service;

import com.password.validator.passwordvalidator.modal.PasswordResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidatorServiceImpl implements PasswordvalidatorService {

    Logger logger = LoggerFactory.getLogger(PasswordValidatorServiceImpl.class);

    @Override
    public PasswordResponse validatePasswordService(String isValidPassword) {

        logger.info("Password to Validate is : {}", isValidPassword);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int counter = 0;

        jsonObject.put("counter", counter);

        PasswordResponse passwordResponse = new PasswordResponse();
        passwordResponse.setStatus(HttpStatus.OK);

        if(isValidPassword.length() <= 9 ){
            logger.info("Size of password is : {}" , isValidPassword.length());
            jsonObject.put("counter", ++counter);
            jsonArray.put("Length of password is less or equal to 8.");
        }

        if(jsonArray.length()==0){
            passwordResponse.setMessage("Password Accepted");
        }else{
            passwordResponse.setStatus(HttpStatus.BAD_REQUEST);
            passwordResponse.setMessage(jsonArray.toString());
        }

        return passwordResponse;
    }
}
