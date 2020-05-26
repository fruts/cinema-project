package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.User;

public interface UserDao {

    User add(User user);

    User findByEmail(String email);
}
