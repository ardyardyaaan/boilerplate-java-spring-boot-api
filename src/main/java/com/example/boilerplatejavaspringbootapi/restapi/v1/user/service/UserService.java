package com.example.boilerplatejavaspringbootapi.restapi.v1.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.boilerplatejavaspringbootapi.helper.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.entity.RoleEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.repository.RoleRepository;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.repository.UserRepository;
import com.example.boilerplatejavaspringbootapi.restapi.v1.userDetail.entity.UserDetailEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.userDetail.repository.UserDetailRepository;

/**
 *
 * @author ardyardyaaan
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    public ResponseEntity<?> createSuperadmin(ResponseObject response) {

        RoleEntity checkRoleSuperadmin = roleRepository.findByRoleName("superadmin");
        if (checkRoleSuperadmin != null) {
            response.setMessage("Superadmin Role Already Created!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        RoleEntity role = new RoleEntity(
                "superadmin",
                "2023-01-01",
                "2100-01-01",
                1,
                0,
                new Date());

        roleRepository.save(role);

        UserEntity user = new UserEntity(
                "superadmin@admin.com",
                "superadmin@1",
                "superadmin",
                "2023-01-01",
                "2100-01-01",
                1,
                0,
                0,
                new Date());

        userRepository.save(user);

        UserDetailEntity userDetail = new UserDetailEntity(
                user,
                role,
                1,
                0,
                new Date());

        userDetailRepository.save(userDetail);

        response.setStatus("success");
        response.setMessage("Data was Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
