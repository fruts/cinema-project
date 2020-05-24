package com.dev.cinemaproject.security;

import com.dev.cinemaproject.exception.AuthenticationException;
import com.dev.cinemaproject.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
