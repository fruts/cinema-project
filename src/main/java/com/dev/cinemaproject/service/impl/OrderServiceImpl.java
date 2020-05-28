package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.OrderDao;
import com.dev.cinemaproject.lib.Inject;
import com.dev.cinemaproject.lib.Service;
import com.dev.cinemaproject.model.Order;
import com.dev.cinemaproject.model.Ticket;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        return orderDao.add(new Order(List.copyOf(tickets), user, LocalDateTime.now()));
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
