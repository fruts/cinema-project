package com.dev.cinemaproject.security;

import com.dev.cinemaproject.exception.AuthenticationException;
import com.dev.cinemaproject.lib.Inject;
import com.dev.cinemaproject.lib.Service;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.UserService;
import com.dev.cinemaproject.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email).orElseThrow(()
                -> new AuthenticationException("Incorrect EMAIL or PASSWORD"));
        if (HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Incorrect EMAIL or PASSWORD");
    }

    @Override
    public User register(String email, String password) {
        byte[] salt = HashUtil.getSalt();
        User user = new User();
        user.setEmail(email);
        user.setSalt(HashUtil.getSalt());
        user.setSalt(salt);
        user.setPassword(HashUtil.hashPassword(password, salt));
        return userService.add(user);
    }
}
