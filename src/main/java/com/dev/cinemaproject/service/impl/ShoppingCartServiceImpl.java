package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.ShoppingCartDao;
import com.dev.cinemaproject.dao.TicketDao;
import com.dev.cinemaproject.lib.Inject;
import com.dev.cinemaproject.lib.Service;
import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.model.ShoppingCart;
import com.dev.cinemaproject.model.Ticket;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket(movieSession, user);
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getTickets().add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        shoppingCartDao.add(new ShoppingCart(user));
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
