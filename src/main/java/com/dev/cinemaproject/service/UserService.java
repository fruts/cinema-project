package com.dev.cinemaproject.service;

import com.dev.cinemaproject.model.User;

public interface UserService {

    User add(User user);

    User findByEmail(String email);
}
