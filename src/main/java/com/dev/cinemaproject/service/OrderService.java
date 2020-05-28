package com.dev.cinemaproject.service;

import com.dev.cinemaproject.model.Order;
import com.dev.cinemaproject.model.Ticket;
import com.dev.cinemaproject.model.User;
import java.util.List;

public interface OrderService {

    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
