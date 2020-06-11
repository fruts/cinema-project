package com.dev.cinemaproject.service;

import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.model.ShoppingCart;
import com.dev.cinemaproject.model.User;

public interface ShoppingCartService {

    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
