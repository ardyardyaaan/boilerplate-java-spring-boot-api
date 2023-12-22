package com.example.boilerplatejavaspringbootapi.restapi.v1.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author ardyardyaaan
 */

public class AuthDto implements Serializable {
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
