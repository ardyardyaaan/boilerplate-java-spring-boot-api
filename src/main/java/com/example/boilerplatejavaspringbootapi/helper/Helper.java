package com.example.boilerplatejavaspringbootapi.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.boilerplatejavaspringbootapi.responses.ResponseObject;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.repository.UserRepository;

/**
 *
 * @author ardyardyaaan
 */

public class Helper {

    @Autowired
    private UserRepository userRepository;
    
    public String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String passwordEncripted;

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        passwordEncripted = sb.toString();

        return passwordEncripted;
    }

    public String generateToken(UserEntity user, String password, String salt) throws NoSuchAlgorithmException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String today = dateFormat.format(date);

        String tokenEncripted;
        salt = user.getUserId() + salt + today + "secretKey";

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        tokenEncripted = sb.toString();

        return tokenEncripted;
    }

    public ResponseEntity<?> checkToken(HttpServletRequest request) throws ParseException {
        ResponseObject response = new ResponseObject("failed", null, "Internal Server Error!");
        String token = request.getHeader("token");

        if (token != null && !token.isEmpty()) {
            UserEntity checkToken = userRepository.findByToken(token);
            if (checkToken != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String strTokenExpired = checkToken.getTokenExpired().contains(":") ? checkToken.getTokenExpired() : checkToken.getTokenExpired() + " 00:00";

                Date tokenExpired = formatter.parse(strTokenExpired);
                Date today = formatter.parse(formatter.format(new Date()));

                if (today.compareTo(tokenExpired) > 0) {
                    response.setMessage("Token Expired!");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    if (checkToken.getStatus() != 1) {
                        response.setMessage("Your Account is Inactive, Please Contact Your Administrator");
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    return new ResponseEntity<>(new ResponseObject("success", null, "Token Valid!"), HttpStatus.CONTINUE);
                }
            } else {
                response.setMessage("Someone Logged In to Your Account on Another Device!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.setMessage("Token Required!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public UserEntity dataToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserEntity dataToken = userRepository.findByToken(token);

        return dataToken;
    }
}
