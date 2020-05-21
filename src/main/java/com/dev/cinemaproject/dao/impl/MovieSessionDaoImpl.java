package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.MovieSessionDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.lib.Dao;
import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            LOGGER.info("Moviesession: " + movieSession
                    + "was added successfully");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("UNABLE TO ADD MOVIESESSION: "
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session
                    .createQuery("FROM MovieSession WHERE showTime > :date");
            query.setParameter("date",date.atStartOfDay());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve available sessions", e);
        }
    }
}
