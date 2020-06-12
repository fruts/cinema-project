package com.dev.cinemaproject.security;

import com.dev.cinemaproject.exception.AuthenticationException;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.ShoppingCartService;
import com.dev.cinemaproject.service.UserService;
import com.dev.cinemaproject.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final HashUtil hashUtil;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     HashUtil hashUtil,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.hashUtil = hashUtil;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email).orElseThrow(()
                -> new AuthenticationException("Incorrect EMAIL or PASSWORD"));
        if (hashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword())) {
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
        user.setPassword(hashUtil.hashPassword(password, salt));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
