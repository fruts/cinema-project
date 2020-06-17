package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.TicketDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.model.Ticket;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            LOGGER.info("ticket add successfully");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add ticket", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Ticket> tickets =
                    session.createQuery("FROM Ticket ", Ticket.class);
            return tickets.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get all tickets", e);
        }
    }

    @Override
    public Ticket getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get ticket with this ID: " + id, e);
        }
    }
}
