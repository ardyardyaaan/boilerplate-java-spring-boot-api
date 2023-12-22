package com.example.boilerplatejavaspringbootapi.restapi.v1.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boilerplatejavaspringbootapi.responses.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.auth.dto.AuthDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.auth.service.AuthService;

/**
 *
 * @author ardyardyaaan
 */

 @RestController
 @RequestMapping("/public/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDto request, Errors errors, BindingResult result) throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        if (errors.hasErrors()) {
            FieldError errorEmail = result.getFieldError("email");
            FieldError errorPassword = result.getFieldError("password");

            if (errorEmail != null) {
                response.setMessage("Email Cannot be Empty");
                return new ResponseEntity(response, HttpStatus.OK);
            } else if (errorPassword != null) {
                response.setMessage("Password Cannot be Empty");
                return new ResponseEntity(response, HttpStatus.OK);
            }
        }

        try {
            return authService.login(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
