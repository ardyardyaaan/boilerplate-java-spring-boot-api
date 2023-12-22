package com.example.boilerplatejavaspringbootapi.restapi.v1.user.controller;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boilerplatejavaspringbootapi.responses.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.service.UserService;

/**
 *
 * @author ardyardyaaan
 */

@RestController
@RequestMapping("/public/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/create/superadmin")
    public ResponseEntity<?> create()
            throws ParseException, NoSuchAlgorithmException, MailException, MessagingException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        try {
            return userService.createSuperadmin(response);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
