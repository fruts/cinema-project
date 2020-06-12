package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.User;
import java.util.Optional;

public interface UserDao {

    User add(User user);

    Optional<User> findByEmail(String email);

    User findById(Long id);
}
