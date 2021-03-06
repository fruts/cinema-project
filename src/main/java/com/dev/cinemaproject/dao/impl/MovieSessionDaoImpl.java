package com.dev.cinemaproject.dao.impl;

import com.dev.cinemaproject.dao.MovieSessionDao;
import com.dev.cinemaproject.exception.DataProcessingException;
import com.dev.cinemaproject.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
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
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> movieSessions =
                    session.createQuery("FROM MovieSession ", MovieSession.class);
            return movieSessions.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get all movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session
                    .createQuery("FROM MovieSession WHERE showTime > :date");
            query.setParameter("date",date.atStartOfDay());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't retrieve available sessions", e);
        }
    }

    @Override
    public MovieSession getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Unable to find movie session with this ID: "
                    + id, e);
        }
    }
}
