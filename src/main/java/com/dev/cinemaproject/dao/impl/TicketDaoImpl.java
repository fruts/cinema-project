package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.TicketDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.lib.Dao;
import com.dev.cinemaproject.model.Ticket;
import com.dev.cinemaproject.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
}
