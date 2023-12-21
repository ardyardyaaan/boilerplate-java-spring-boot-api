package com.example.boilerplatejavaspringbootapi.restapi.v1.user.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
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

import com.example.boilerplatejavaspringbootapi.helper.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.dto.UserDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.service.UserService;

/**
 *
 * @author ardyardyaaan
 */

@RestController
@RequestMapping("/public/api/v1/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    UserService userService;

    @PostMapping("/create/superadmin")
    public ResponseEntity<?> create()
            throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        try {
            return userService.createSuperadmin(response);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
