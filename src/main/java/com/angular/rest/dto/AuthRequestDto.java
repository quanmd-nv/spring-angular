package com.angular.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthRequestDto {

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    private Boolean rememberMe;
}
