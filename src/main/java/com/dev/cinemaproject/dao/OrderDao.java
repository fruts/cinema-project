package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.Order;
import com.dev.cinemaproject.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getOrderHistory(User user);
}
