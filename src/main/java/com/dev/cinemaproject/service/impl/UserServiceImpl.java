package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.UserDao;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userDao.getById(id);
    }
}
