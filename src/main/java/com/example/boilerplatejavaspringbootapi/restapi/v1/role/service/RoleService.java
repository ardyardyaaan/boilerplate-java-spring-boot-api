package com.example.boilerplatejavaspringbootapi.restapi.v1.role.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.boilerplatejavaspringbootapi.helper.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.dto.RoleDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.entity.RoleEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.repository.RoleRepository;

/**
 *
 * @author ardyardyaaan
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> create(ResponseObject response, RoleDto request) {

        String roleName = request.getRole_name().trim().toLowerCase();
        RoleEntity checkRoleName = roleRepository.findByRoleName(roleName);

        if (checkRoleName != null) {
            response.setMessage("Role Name Already Exist");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        RoleEntity role = new RoleEntity(
                roleName,
                request.getStart_date(),
                request.getEnd_date(),
                1,
                0,
                new Date());

        roleRepository.save(role);

        response.setStatus("success");
        response.setMessage("Data was Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
