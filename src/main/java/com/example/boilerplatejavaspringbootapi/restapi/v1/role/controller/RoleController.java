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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.boilerplatejavaspringbootapi.helper.Helper;
import com.example.boilerplatejavaspringbootapi.request.RequestListDto;
import com.example.boilerplatejavaspringbootapi.responses.ResponseList;
import com.example.boilerplatejavaspringbootapi.responses.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.dto.RoleDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.service.RoleService;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;

/**
 *
 * @author ardyardyaaan
 */

@RestController
@RequestMapping("/public/api/v1/role")
public class RoleController extends Helper {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RoleDto roleRequest, Errors errors, BindingResult result)
            throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        try {
            ResponseEntity<?> token = checkToken(request);
            if (token.getStatusCodeValue() == 100) {
                if (errors.hasErrors()) {
                    FieldError errorRoleName = result.getFieldError("role_name");
                    FieldError errorStartDate = result.getFieldError("start_date");
                    FieldError errorEndDate = result.getFieldError("end_date");
                    FieldError errorStatus = result.getFieldError("status");
    
                    if (errorRoleName != null) {
                        response.setMessage("Role Name Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    } else if (errorStartDate != null) {
                        response.setMessage("Valid From Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    } else if (errorEndDate != null) {
                        response.setMessage("Valid To Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    } else if (errorStatus != null) {
                        response.setMessage("Status Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    }
                }
    
                UserEntity dataToken = dataToken(request);
                return roleService.create(response, roleRequest, dataToken);
            } else {
                return token;
            }
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(RequestListDto requestParam) throws ParseException {
        ResponseList response = new ResponseList("failed", null, "Internal Server Error!");

        try {
            ResponseEntity<?> token = checkToken(request);
            if (token.getStatusCodeValue() == 100) {
                UserEntity dataToken = dataToken(request);
                return roleService.list(response, requestParam, dataToken);
            } else {
                return token;
            }
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody RoleDto roleRequest, Errors errors, BindingResult result) throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        try {
            ResponseEntity<?> token = checkToken(request);
            if (token.getStatusCodeValue() == 100) {
                if (roleRequest.getRole_id() == null) {
                    response.setMessage("Role ID Cannot be Empty");
                    return new ResponseEntity(response, HttpStatus.OK);
                }
    
                if (errors.hasErrors()) {
                    FieldError errorRoleName = result.getFieldError("role_name");
                    FieldError errorStartDate = result.getFieldError("start_date");
                    FieldError errorEndDate = result.getFieldError("end_date");
                    FieldError errorStatus = result.getFieldError("status");
    
                    if (errorRoleName != null) {
                        response.setMessage("Role Name Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    } else if (errorStartDate != null) {
                        response.setMessage("Valid From Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    } else if (errorEndDate != null) {
                        response.setMessage("Valid To Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    } else if (errorStatus != null) {
                        response.setMessage("Status Cannot be Empty");
                        return new ResponseEntity(response, HttpStatus.OK);
                    }
                }
    
                UserEntity dataToken = dataToken(request);
                return roleService.update(response, roleRequest, dataToken);
            } else {
                return token;
            }
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam("id") Integer id) throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");

        try {
            ResponseEntity<?> token = checkToken(request);
            if (token.getStatusCodeValue() == 100) {
                UserEntity dataToken = dataToken(request);
                return roleService.detail(response, id, dataToken);
            } else {
                return token;
            }
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
