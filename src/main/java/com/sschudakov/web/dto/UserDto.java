package com.sschudakov.web.dto;

import com.sschudakov.web.registration.validation.ValidEmail;
import com.sschudakov.web.registration.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull
    @Size(min = 1)
    private String userName;

    @ValidPassword
    private String userPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1)
    private String email;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
