package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);
}
