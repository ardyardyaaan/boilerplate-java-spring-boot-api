package com.example.boilerplatejavaspringbootapi.restapi.v1.role.controller;

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
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.dto.RoleDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.service.RoleService;

/**
 *
 * @author ardyardyaaan
 */

@RestController
@RequestMapping("/public/api/v1/role")
public class RoleController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RoleDto roleRequest, Errors errors, BindingResult result)
            throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        try {
            if (errors.hasErrors()) {
                FieldError errorRoleName = result.getFieldError("role_name");
                FieldError errorStartDate = result.getFieldError("start_date");
                FieldError errorEndDate = result.getFieldError("end_date");

                if (errorRoleName != null) {
                    response.setMessage("Role Name Cannot be Empty");
                    return new ResponseEntity(response, HttpStatus.OK);
                } else if (errorStartDate != null) {
                    response.setMessage("Valid From Cannot be Empty");
                    return new ResponseEntity(response, HttpStatus.OK);
                } else if (errorEndDate != null) {
                    response.setMessage("Valid To Cannot be Empty");
                    return new ResponseEntity(response, HttpStatus.OK);
                }
            }

            return roleService.create(response, roleRequest);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
