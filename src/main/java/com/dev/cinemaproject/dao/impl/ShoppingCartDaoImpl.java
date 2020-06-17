package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.ShoppingCartDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.model.ShoppingCart;
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
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            LOGGER.info("Shopping cart was added successfully");
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("unable to add shopping cart", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> carts =
                    session.createQuery("FROM ShoppingCart ", ShoppingCart.class);
            return carts.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get all shopping carts", e);
        }
    }

    @Override
    public ShoppingCart getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ShoppingCart.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get shopping cart with this ID: " + id, e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session
                    .createQuery("from ShoppingCart c "
                            + "left join fetch c.tickets Ticket "
                            + " where c.user =: user", ShoppingCart.class);
            query.setParameter("user", user);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get shopping cart by user", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            LOGGER.info("Shopping cart was updated successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to update shopping cart", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
