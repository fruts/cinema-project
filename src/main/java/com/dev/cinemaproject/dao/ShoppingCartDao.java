package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.ShoppingCart;
import com.dev.cinemaproject.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
