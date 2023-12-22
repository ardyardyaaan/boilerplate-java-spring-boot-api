package com.example.boilerplatejavaspringbootapi.restapi.v1.auth.service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.boilerplatejavaspringbootapi.helper.Helper;
import com.example.boilerplatejavaspringbootapi.responses.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.auth.dto.AuthDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.repository.UserRepository;

/**
 *
 * @author ardyardyaaan
 */

 @Service
public class AuthService extends Helper {
    
    @Autowired
    private UserRepository userRepository;
    
    public ResponseEntity<?> login(AuthDto request, ResponseObject response) throws NoSuchAlgorithmException, ParseException {
        UserEntity user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            if (user.getStatus().equals(0)) {
                response.setMessage("Your Account is Inactive, Please Contact Your Administrator");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            String hashPassword = hashPassword(request.getPassword(), "10");
            if (user.getPassword().equalsIgnoreCase(hashPassword)) {
                Optional<?> result = Optional.empty();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 30);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                String token = generateToken(user, request.getPassword(), "100");
                String tokenExpired = formatter.format(cal.getTime());

                user.setToken(token);
                user.setTokenExpired(tokenExpired);
                user.setUpdatedAt(new Date());
                user.setUpdatedBy(user.getUserId());
                userRepository.save(user);

                List<?> users = new ArrayList(Arrays.asList(user));
                result = users.stream().findFirst();

                response.setStatus("success");
                response.setData(result);
                response.setMessage("Login Success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMessage("Password is Not Correct");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.setMessage("The Email Address Provided is Not Registered. Please Contact Your Administrator");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    
}
