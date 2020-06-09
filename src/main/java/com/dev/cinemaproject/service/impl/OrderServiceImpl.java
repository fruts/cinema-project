package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.OrderDao;
import com.dev.cinemaproject.model.Order;
import com.dev.cinemaproject.model.Ticket;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        return orderDao.add(new Order(List.copyOf(tickets), user, LocalDateTime.now()));
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
