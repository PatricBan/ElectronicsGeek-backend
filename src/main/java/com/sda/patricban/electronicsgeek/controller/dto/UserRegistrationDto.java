package com.sda.patricban.electronicsgeek.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 30)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 30)
    private String confirmPassword;
}
