package com.dev.cinemaproject.model.dto;

import com.dev.cinemaproject.annotation.EmailConstraint;
import com.dev.cinemaproject.annotation.PasswordMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatch
public class UserRequestDto {
    @EmailConstraint
    private String email;
    @NotNull(message = "Password must be not null")
    @Size(min = 8, message = "Password must have minimum 8 symbols")
    private String password;
    @NotNull(message = "Repeat password must be not null")
    private String repeatPassword;
}
