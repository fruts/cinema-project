package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.UserDao;
import com.dev.cinemaproject.lib.Inject;
import com.dev.cinemaproject.lib.Service;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.UserService;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
