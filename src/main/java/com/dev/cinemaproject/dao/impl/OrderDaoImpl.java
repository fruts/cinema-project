package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.OrderDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.model.Order;
import com.dev.cinemaproject.model.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            LOGGER.info(order + " was added to DB");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add order", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> orders =
                    session.createQuery("FROM Order ", Order.class);
            return orders.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get all orders", e);
        }
    }

    @Override
    public Order getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get order with this ID: " + id, e);
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session
                    .createQuery("select distinct o from Order o "
                            + "left join fetch o.tickets Ticket "
                            + "where o.user =: user", Order.class);
            query.setParameter("user", user);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find orders of user with id "
                    + user.getId(), e);
        }
    }
}
